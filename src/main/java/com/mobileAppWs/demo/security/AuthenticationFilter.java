package com.mobileAppWs.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobileAppWs.demo.SpringApplicationContext;
import com.mobileAppWs.demo.common.dto.UserDto;
import com.mobileAppWs.demo.models.request.UserLoginRequestModel;
import com.mobileAppWs.demo.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
  public AuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
    try{
      UserLoginRequestModel creds = new ObjectMapper()
              .readValue(req.getInputStream(), UserLoginRequestModel.class);

              logger.info("creds ==== > " + creds.getEmail() +creds.getPassword());
      return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      creds.getEmail(),
                      creds.getPassword(),
                      new ArrayList<>()
              )
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
    String userName = ((User) auth.getPrincipal()).getUsername();
    logger.info("userName ==== > " + userName);
    String token = Jwts.builder()
            .setSubject(userName)
            .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
            .compact();
    UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
    UserDto userDto = userService.getUser(userName);


    res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    res.addHeader("UserID", userDto.getUserId());
  }
}
