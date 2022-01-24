package com.mobileAppWs.demo.repository;

import com.mobileAppWs.demo.io.entitiy.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


//public interface UserRepository extends CrudRepository<UserEntity, Long>
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>
{
  UserEntity findByEmail(String email);
  UserEntity findByUserId(String userId);

}
