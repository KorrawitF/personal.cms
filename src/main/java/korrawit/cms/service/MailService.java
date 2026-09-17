package korrawit.cms.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HexFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import korrawit.cms.domain.entity.MailTransaction;
import korrawit.cms.domain.repository.MailTransactionRepository;
import korrawit.cms.error.MailDeliveryException;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final Session mailSession;
    private final OAuthTokenService oAuthTokenService;
    private final MailTransactionRepository mailTransactionRepository;
    private final String fromAddress;
    private final String host;
    private final int port;

    public MailService(Session mailSession, OAuthTokenService oAuthTokenService,
            MailTransactionRepository mailTransactionRepository,
            @Value("${app.mail.from}") String fromAddress,
            @Value("${app.mail.host}") String host,
            @Value("${app.mail.port}") int port) {
        this.mailSession = mailSession;
        this.oAuthTokenService = oAuthTokenService;
        this.mailTransactionRepository = mailTransactionRepository;
        this.fromAddress = fromAddress;
        this.host = host;
        this.port = port;
    }

    @Async
    public void send(String to, String subject, String body) {
        try {
            MimeMessage message = new MimeMessage(mailSession);
            if (fromAddress != null && !fromAddress.isBlank()) {
                message.setFrom(new InternetAddress(fromAddress));
            }
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            String accessToken = oAuthTokenService.getAccessToken();
            try (Transport transport = mailSession.getTransport("smtp")) {
                transport.connect(host, port, fromAddress, accessToken);
                transport.sendMessage(message, message.getAllRecipients());
            }
        } catch (Exception e) {
            log.error("Failed to send mail to {}: {}", to, e.getMessage());
            throw new MailDeliveryException("Could not send mail to " + to, e);
        }

        Instant now = Instant.now();
        mailTransactionRepository.save(new MailTransaction(null, hash(to), subject, body, now, now));
    }

    private static String hash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
