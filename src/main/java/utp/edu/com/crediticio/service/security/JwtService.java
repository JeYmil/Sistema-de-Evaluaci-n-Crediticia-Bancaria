package utp.edu.com.crediticio.service.security;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.stereotype.Service;
import utp.edu.com.crediticio.service.AutenticacionService;

@Service
public class JwtService {
    private final JwtEncoder encoder;
    private final String issuer;
    private final long expirationMinutes;

    public JwtService(JwtEncoder encoder,
            @Value("${app.jwt.issuer}") String issuer,
            @Value("${app.jwt.expiration-minutes:60}") long expirationMinutes) {
        this.encoder = encoder;
        this.issuer = issuer;
        this.expirationMinutes = expirationMinutes;
    }

    public Token emitir(AutenticacionService.Cuenta cuenta) {
        Instant ahora = Instant.now();
        Instant vence = ahora.plus(Duration.ofMinutes(expirationMinutes));
        String rol = "ROLE_" + cuenta.tipo();
        String asunto = cuenta.tipo().toLowerCase() + ":" + cuenta.id();
        var claims = JwtClaimsSet.builder()
                .issuer(issuer)
                .subject(asunto)
                .issuedAt(ahora)
                .expiresAt(vence)
                .claim("accountId", cuenta.id())
                .claim("tipo", cuenta.tipo())
                .claim("roles", List.of(rol))
                .build();
        var header = JwsHeader.with(MacAlgorithm.HS256).build();
        String valor = encoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new Token(valor, expirationMinutes * 60);
    }

    public record Token(String accessToken, long expiresInSeconds) { }
}
