package cz.upce.nnpia_semestralni_prace.util;

import cz.upce.nnpia_semestralni_prace.dto.UserDetailsDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtUtil {
    public static final String JWT_SECRET = "eyJhbGciOiJIUzUxMiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY3Nzc4MDU1MCwiaWF0IjoxNjc3NzgwNTUwfQ.3lC1OMK7IJxEV_gO5yMmkD8lgOpKPf3yIeQ5SYjniAReRQzYDMipPW9FOaEG7vild_EXMgpbd7nycv5TNAbxCQ"; // can be generated dynamically

    private static final long JWT_EXPIRATION_MS = 60 * 60 * 1000; // 1 hour

    private static final Key JWT_KEY = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String extractUsername(String token) {
        Jwt<?, Claims> jwt = Jwts.parser().setSigningKey(JWT_KEY).parse(token);
        return jwt.getBody().get("sub", String.class);
    }

    public String generateJwtToken(Authentication authentication) {
        UserDetailsDto userPrincipal = (UserDetailsDto) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWT_EXPIRATION_MS))
                .signWith(JWT_KEY, SignatureAlgorithm.HS512)
                .compact();
    }
}
