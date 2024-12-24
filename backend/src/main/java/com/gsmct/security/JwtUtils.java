package com.gsmct.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

@Component
@Slf4j
public class JwtUtils {

  @Value("${auth.jwt.expiration.in.mins:60}")
  private int jwtExpirationInMins;

  private final SecretKey jwtSecret = Jwts.SIG.HS256.key().build();

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
    Instant now = Instant.now().truncatedTo(SECONDS);
    return Jwts.builder().subject(userPrincipal.getUsername()).issuedAt(Date.from(now))
      .expiration(Date.from(now.plus(jwtExpirationInMins, MINUTES))).signWith(jwtSecret).compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().verifyWith(jwtSecret).build().parseSignedClaims(token).getPayload().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().verifyWith(jwtSecret).build().parseSignedClaims(authToken);
      return true;
    } catch (MalformedJwtException e) {
      log.error("Invalid JWT token: {}", e.getMessage(), e);
    } catch (ExpiredJwtException e) {
      log.error("JWT token is expired: {}", e.getMessage(), e);
    } catch (UnsupportedJwtException e) {
      log.error("JWT token is unsupported: {}", e.getMessage(), e);
    } catch (IllegalArgumentException e) {
      log.error("JWT claims string is empty: {}", e.getMessage(), e);
    }
    return false;
  }
}
