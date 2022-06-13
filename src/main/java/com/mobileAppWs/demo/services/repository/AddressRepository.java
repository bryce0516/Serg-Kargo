package com.mobileAppWs.demo.services.repository;


import com.mobileAppWs.demo.io.entitiy.AddressEntity;
import com.mobileAppWs.demo.io.entitiy.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
  List<AddressEntity> findAllByUserDetails(UserEntity userEntity);
  AddressEntity findAllByAddressId(String addressId);
}
