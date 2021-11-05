package com.mobileAppWs.demo.controllers;

import com.mobileAppWs.demo.common.dto.UserDto;
import com.mobileAppWs.demo.exceptions.UserServiceException;
import com.mobileAppWs.demo.models.request.UserDetailsRequestModel;
import com.mobileAppWs.demo.models.response.ErrorMessages;
import com.mobileAppWs.demo.models.response.UserRest;
import com.mobileAppWs.demo.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

  @Autowired UserService userService;

  @GetMapping(
      path = "/{id}",
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  public UserRest getUser(@PathVariable String id) {
    UserRest returnValue = new UserRest();

    UserDto userDto = userService.getUserByUserId(id);
    BeanUtils.copyProperties(userDto, returnValue);

    return returnValue;
  }

  @PostMapping(
          consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
          produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
  )
  public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

    UserRest returnValue = new UserRest();

    if(userDetails.getFirstName().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userDetails, userDto);

    UserDto createdUser = userService.createUser(userDto);
    BeanUtils.copyProperties(createdUser, returnValue);

    return returnValue;
  }

  @PatchMapping
  public String updateUser() {
    return "update user was called";
  }

  @DeleteMapping
  public String deleteUser() {
    return "delete user was called";
  }
}
