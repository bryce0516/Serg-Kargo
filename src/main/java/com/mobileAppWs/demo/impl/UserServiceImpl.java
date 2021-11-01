package com.mobileAppWs.demo.impl;

import com.mobileAppWs.demo.common.dto.UserDto;
import com.mobileAppWs.demo.io.entitiy.UserEntity;
import com.mobileAppWs.demo.repository.UserRepository;
import com.mobileAppWs.demo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDto createUser(UserDto user) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(user, userEntity);

    userEntity.setEncryptedPassword("test");
    userEntity.setUserId("testUserId");

    UserEntity storedUserDetails = userRepository.save(userEntity);

    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(storedUserDetails, returnValue);

    return returnValue;
  }
}
