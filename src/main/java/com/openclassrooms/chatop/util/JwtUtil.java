package com.openclassrooms.chatop.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Optional;

import javax.crypto.SecretKey;

public class JwtUtil {

  public static final SecretKey SECRET_KEY = Jwts.SIG.HS512.key().build();

  public static String generateJwtToken(Integer id) {
    String token = Jwts.builder()
        .subject(String.valueOf(id))
        .signWith(SECRET_KEY)
        .compact();

    return token;
  }

  public static boolean isTokenValid(String token) {
    try {
      Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
      return true;
    } catch (Exception exception) {
      // Token validation failed
      return false;
    }
  }

  public static Optional<Integer> extractUserId(String token) {
    try {
      Jws<Claims> claimsJws = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token);
      Claims claims = claimsJws.getPayload();
      return Optional.of(Integer.parseInt(claims.getSubject()));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  public static String extractJwtFromHeader(String authorizationHeader) {
    return authorizationHeader.replace("Bearer ", "");
  }
}