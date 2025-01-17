package uz.chat.app.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uz.chat.app.exception.InvalidDataException;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {
    private static final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556B58703272357538782F413F44";


    public SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }


    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000l * 60 * 60 * 24 * 30)) //expire time 30 day
                .signWith(getSigningKey())
                .compact();
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15)) //expire time 15 minute
                .signWith(getSigningKey())
                .compact();
    }

    public String refreshAccessToken(String refreshToken) {
        if (validateToken(refreshToken)) {
            return generateAccessToken(getUsernameFromToken(refreshToken));
        }else {
            throw new InvalidDataException("refresh token fail");
        }
    }

    public boolean validateToken(String token) {
        try {
            Claims payload = getPayload(token);
            Date expiration = payload.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            log.info("Token is not valid: {}", e.getMessage());
        }
        return false;
    }

    public Claims getPayload(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String getUsernameFromToken(String token) {
        Claims payload = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return payload.getSubject();
    }
}
