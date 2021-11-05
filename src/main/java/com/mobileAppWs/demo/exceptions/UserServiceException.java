package com.mobileAppWs.demo.exceptions;

public class UserServiceException extends RuntimeException {

  private static final long serialVersionUID = -9000342841811264863L;

  public UserServiceException(String message){
    super(message);
  }
}
