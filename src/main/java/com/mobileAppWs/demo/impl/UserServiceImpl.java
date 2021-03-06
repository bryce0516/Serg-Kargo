package com.mobileAppWs.demo.impl;

import com.mobileAppWs.demo.common.AmazonSES;
import com.mobileAppWs.demo.common.Utils;
import com.mobileAppWs.demo.common.dto.AddressDTO;
import com.mobileAppWs.demo.common.dto.UserDto;
import com.mobileAppWs.demo.exceptions.UserServiceException;
import com.mobileAppWs.demo.io.entitiy.UserEntity;
import com.mobileAppWs.demo.models.response.ErrorMessages;
import com.mobileAppWs.demo.services.repository.UserRepository;
import com.mobileAppWs.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;

  @Autowired Utils utils;

  @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDto createUser(UserDto user) {

    if (userRepository.findByEmail(user.getEmail()) != null)
      throw new RuntimeException("Record already exists");

    for (int i = 0; i < user.getAddresses().size(); i++) {
      AddressDTO address = user.getAddresses().get(i);
      address.setUserDetails(user);
      address.setAddressId(utils.generateAddressId(30));
      user.getAddresses().set(i, address);
    }
    // UserEntity userEntity = new UserEntity()
    //    BeanUtils.copyProperties(user, userEntity);
    ModelMapper modelMapper = new ModelMapper();
    UserEntity userEntity = modelMapper.map(user, UserEntity.class);

    String publicUserId = utils.generateUserId(30);

    userEntity.setUserId(publicUserId);
    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
    userEntity.setEmailVerificationStatus(false);
    UserEntity storedUserDetails = userRepository.save(userEntity);

    //    UserDto returnValue = new UserDto();
    //    BeanUtils.copyProperties(storedUserDetails, returnValue);
    UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

    //
    new AmazonSES().verifyEmail(returnValue);

    return returnValue;
  }

  @Override
  public UserDto updateUser(String userId, UserDto user) {
    UserDto returnValue = new UserDto();
    UserEntity userEntity = userRepository.findByUserId(userId);

    if (userEntity == null)
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());

    UserEntity updatedUserDetails = userRepository.save(userEntity);
    BeanUtils.copyProperties(updatedUserDetails, returnValue);
    return returnValue;
  }

  @Override
  public UserDto getUser(String email) {

    UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null) throw new UsernameNotFoundException(email);

    UserDto returnValue = new UserDto();
    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByEmail(email);
    if (userEntity == null) throw new UsernameNotFoundException(email);

    return new User(
        userEntity.getEmail(),
        userEntity.getEncryptedPassword(),
        userEntity.getEmailVerificationStatus(),
        true,
        true,
        true,
        new ArrayList<>());
    //    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new
    // ArrayList<>());
  }

  @Override
  public UserDto getUserByUserId(String userId) {
    UserDto returnValue = new UserDto();
    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null)
      throw new UsernameNotFoundException("User with ID: " + userId + " not found");

    BeanUtils.copyProperties(userEntity, returnValue);
    return returnValue;
  }

  @Transactional
  @Override
  public void deleteUser(String userId) {
    UserEntity userEntity = userRepository.findByUserId(userId);
    if (userEntity == null)
      throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

    userRepository.delete(userEntity);
  }

  @Override
  public List<UserDto> getUsers(int page, int limit) {
    List<UserDto> returnValue = new ArrayList<>();

    if (page > 0) page = page - 1;

    Pageable pageableRequest = PageRequest.of(page, limit);

    Page<UserEntity> usersPage = userRepository.findAll(pageableRequest);
    List<UserEntity> users = usersPage.getContent();

    for (UserEntity userEntity : users) {
      UserDto userDto = new UserDto();
      BeanUtils.copyProperties(userEntity, userDto);
      returnValue.add(userDto);
    }
    return returnValue;
  }

  @Override
  public boolean verifyEmailToken(String token) {
    boolean returnValue = false;
    UserEntity userEntity = userRepository.findByEmailVerificationToken(token);

    if (userEntity != null) {
      boolean hasTokenExipred = Utils.hasTokenExpired(token);

      if (!hasTokenExipred) {
        userEntity.setEmailVerificationToken(null);
        userEntity.setEmailVerificationStatus(Boolean.TRUE);
        userRepository.save(userEntity);
        returnValue = true;
      }
    }
    return returnValue;
  }
}
