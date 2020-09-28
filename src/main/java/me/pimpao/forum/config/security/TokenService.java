package me.pimpao.forum.config.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.pimpao.forum.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jwt.expiration}")
    private Long expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        User logined = (User) authentication.getPrincipal();
        Date today = new Date();
        Long expirationDate = today.getTime() + expiration;
        return Jwts.builder()
                .setIssuer("API Forum")
                .setSubject(logined.getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(expirationDate))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
