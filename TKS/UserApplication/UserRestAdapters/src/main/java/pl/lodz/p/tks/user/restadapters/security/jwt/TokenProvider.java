package pl.lodz.p.tks.user.restadapters.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import pl.lodz.p.tks.user.restadapters.security.utils.DateTimeProvider;

import javax.inject.Inject;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TokenProvider {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private static final String ROLES = "roles";
    private static final long VALID_MINUTES = 15;

    @Inject
    private DateTimeProvider dateTimeProvider;

    public String createToken(String username, Set<String> roles) {
        return createToken(new JwtCredential(username, roles));
    }

    public String createToken(JwtCredential jwtCredential) {
        LocalDateTime now = dateTimeProvider.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(jwtCredential.getUsername())
                .claim(ROLES, rolesToString(jwtCredential.getRoles()))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .setIssuedAt(dateTimeProvider.toDate(now))
                .setExpiration(dateTimeProvider.toDate(now.plusMinutes(VALID_MINUTES)))
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public JwtCredential extractCredential(String validToken) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(validToken)
                .getBody();
        return new JwtCredential(claims.getSubject(), rolesFromString(claims.get(ROLES, String.class)));
    }

    private String rolesToString(Set<String> roles) {
        return String.join(",", roles);
    }

    private Set<String> rolesFromString(String roles) {
        return Arrays.stream(roles.split(",")).collect(Collectors.toSet());
    }
}