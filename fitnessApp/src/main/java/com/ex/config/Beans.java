package com.ex.config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
public class Beans {
    @Bean
    public Key key() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    @Bean
    public JwtBuilder jwtsBuilder(Key key) {
        return Jwts.builder().signWith(key);
    }
}
