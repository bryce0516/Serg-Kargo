package com.mobileAppWs.demo.repository;

import com.mobileAppWs.demo.io.entitiy.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
  UserEntity findUserByEmail(String email);

  UserEntity findByUserId(String userId);

}
