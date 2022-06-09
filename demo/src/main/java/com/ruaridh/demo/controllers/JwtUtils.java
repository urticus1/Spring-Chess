package com.ruaridh.demo.controllers;

import com.ruaridh.demo.entity.Player;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 15 * 60 * 1000; //15 mins

    private final String _secret = "mysecretkey";

    private final JwtParser _parser = Jwts.parser();

    public JwtUtils() {
        _parser.setSigningKey(_secret);
    }

    //retrieve username from jwt token
    public String getPlayerName(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = _parser.parseClaimsJws(token).getBody(); //parsing here validates the token signature.
        return claimsResolver.apply(claims);
    }

    public String generateToken(Player player) {
        Map<String, Object> claims = new HashMap<>();
        return generateToken(claims, player.getName());
    }

    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject) //set the user.
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, _secret).compact();
    }

}
