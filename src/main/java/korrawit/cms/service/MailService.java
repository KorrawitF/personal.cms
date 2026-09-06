package korrawit.cms.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HexFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import korrawit.cms.domain.entity.MailTransaction;
import korrawit.cms.domain.repository.MailTransactionRepository;
import korrawit.cms.error.MailDeliveryException;

@Service
public class MailService {

    private static final Logger log = LoggerFactory.getLogger(MailService.class);

    private final JavaMailSender mailSender;
    private final MailTransactionRepository mailTransactionRepository;
    private final String fromAddress;

    public MailService(JavaMailSender mailSender, MailTransactionRepository mailTransactionRepository,
            @Value("${app.mail.from}") String fromAddress) {
        this.mailSender = mailSender;
        this.mailTransactionRepository = mailTransactionRepository;
        this.fromAddress = fromAddress;
    }

    public void send(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (fromAddress != null && !fromAddress.isBlank()) {
            message.setFrom(fromAddress);
        }
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
        } catch (MailException e) {
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
