package com.mobileAppWs.demo.services;

import com.mobileAppWs.demo.common.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto createUser(UserDto user);
  UserDto updateUser(String userId, UserDto user);
  UserDto getUser(String email);
  UserDto getUserByUserId(String userId);
}
