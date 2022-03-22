package com.mobileAppWs.demo.services;

import com.mobileAppWs.demo.common.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto user);
  UserDto updateUser(String userId, UserDto user);
  UserDto getUser(String email);
  UserDto getUserByUserId(String userId);
  void deleteUser(String userId);
  List<UserDto> getUsers(int page, int limit);
  boolean verifyEmailToken(String token);
}
