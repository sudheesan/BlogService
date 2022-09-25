package edu.mum.cs544.BlogService.helpers;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtHelper {

    @Value("${auth.jwt.secret}")
    private String secret;
    @Value("${auth.jwt.timeout}")
    private long tokenExpiration;

    public String generateToken(String email, Map<String, Object> claimMap) {
        return Jwts.builder().setClaims(claimMap).setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder().setSubject(email).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + tokenExpiration * 60)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }


    public String getUsernameFromToken(String token) {
        String result = null;
        try {
            result = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
            throw e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
