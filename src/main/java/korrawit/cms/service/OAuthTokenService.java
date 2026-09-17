package korrawit.cms.service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@Service
public class OAuthTokenService {

    private static final long EXPIRY_BUFFER_SECONDS = 60;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper;
    private final String clientId;
    private final String refreshToken;
    private final String scope;
    private final String tokenUrl;

    private volatile String accessToken;
    private volatile Instant expiresAt = Instant.EPOCH;

    public OAuthTokenService(ObjectMapper objectMapper,
            @Value("${app.mail.oauth2.client-id}") String clientId,
            @Value("${app.mail.oauth2.refresh-token}") String refreshToken,
            @Value("${app.mail.oauth2.scope}") String scope,
            @Value("${app.mail.oauth2.token-url}") String tokenUrl) {
        this.objectMapper = objectMapper;
        this.clientId = clientId;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.tokenUrl = tokenUrl;
    }

    public synchronized String getAccessToken() {
        if (accessToken == null || !Instant.now().isBefore(expiresAt)) {
            refresh();
        }
        return accessToken;
    }

    private void refresh() {
        String form = "client_id=" + encode(clientId)
                + "&grant_type=refresh_token"
                + "&refresh_token=" + encode(refreshToken)
                + "&scope=" + encode(scope);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(tokenUrl))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to reach the mail OAuth2 token endpoint", e);
        }

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Mail OAuth2 token refresh failed (" + response.statusCode() + "): " + response.body());
        }

        JsonNode json = objectMapper.readTree(response.body());
        String token = json.path("access_token").asString(null);
        if (token == null) {
            throw new IllegalStateException("No access_token in mail OAuth2 token response: " + response.body());
        }
        long expiresIn = json.path("expires_in").asLong(3600);

        this.accessToken = token;
        this.expiresAt = Instant.now().plusSeconds(Math.max(expiresIn - EXPIRY_BUFFER_SECONDS, 0));
    }

    private static String encode(String value) {
        return URLEncoder.encode(value == null ? "" : value, StandardCharsets.UTF_8);
    }
}
