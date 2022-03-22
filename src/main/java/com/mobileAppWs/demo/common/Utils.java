package com.mobileAppWs.demo.common;

import com.mobileAppWs.demo.security.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {

  private final Random RANDOM = new SecureRandom();
  private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private final int ITERATIONS = 10000;
  private final int KEY_LENGTH = 256;

  public String generateUserId(int length) {
    return generateRandomString(length);
  }

  public String generateAddressId(int length) {
    return generateRandomString(length);
  }

  private String generateRandomString(int length) {
    StringBuilder returnValue = new StringBuilder(length);

    for(int i =0; i< length; i++){
      returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
    }

    return new String(returnValue);
  }

  public static boolean hasTokenExpired(String token) {
    Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token).getBody();

    Date tokenExpiationDate = claims.getExpiration();
    Date todayDate = new Date();

    return tokenExpiationDate.before(todayDate);
  }

}
