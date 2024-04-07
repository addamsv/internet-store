package com.example.demo.guard.jwt;

import com.example.demo.endpoints.role.Role;
import com.example.demo.endpoints.users.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${application.security.jwt.token.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.refresh-token.secret-key}")
    private String refreshSecretKey;
    @Value("${application.security.jwt.salt}")
    private String salt;
    @Value("${application.security.jwt.token.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;

    public boolean isPasswordEqual(String stringPw, String hashed) {
        return BCrypt.checkpw(stringPw, hashed);
    }

    public String getPassHash(String password) {
        return BCrypt.hashpw(password, salt);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public List<Role> extractRole(String token) {
        return extractClaim(token, (Claims claim) -> (List<Role>) claim.get("roles"));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    public String generateToken(Users userDetails) {
//        return generateToken(new HashMap<>(), userDetails);
//    }

    public String generateToken(
            Map<String, Object> extraClaims,
            Users userDetails
    ) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, Users userDetails) {
        return buildToken(extraClaims, userDetails, refreshExpiration);
    }

    public boolean isTokenValid(String token, Users userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getEmail())) && ! isTokenExpired(token);
    }

    public boolean isRefreshTokenValid(String token, Users userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getEmail())) && ! isTokenExpired(token);
    }


    /** --=== PRIVATE METHODS ===-- */

    private String buildToken(
            Map<String, Object> extraClaims,
            Users userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}