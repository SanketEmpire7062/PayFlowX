package com.payflowx.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {


    @Value("${jwt.secret}")
    private String secret_key;

    private final long expiration_time = 1000 * 60 * 60; // 1 hour

    private Key getSingingKey(){
        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }


    //generate token
    public String  generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration_time))
                .signWith(getSingingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //extract email
    public String extractuserName(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSingingKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // validate token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSingingKey())
                    .build()
                    .parseClaimsJws(token);
            return true;

        }
        catch (Exception e){
            e.printStackTrace();
            return false;

        }
    }



}
