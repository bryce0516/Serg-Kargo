package com.mobileAppWs.demo.impl;

import com.mobileAppWs.demo.common.dto.AddressDTO;
import com.mobileAppWs.demo.io.entitiy.AddressEntity;
import com.mobileAppWs.demo.io.entitiy.UserEntity;
import com.mobileAppWs.demo.repository.AddressRepository;
import com.mobileAppWs.demo.repository.UserRepository;
import com.mobileAppWs.demo.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  AddressRepository addressRepository;

  @Override
  public List<AddressDTO> getAddress(String userId) {
    List<AddressDTO> returnValue = new ArrayList<>();
    ModelMapper modelMapper = new ModelMapper();

    UserEntity userEntity = userRepository.findByUserId(userId);
    if(userEntity == null) return returnValue;

    Iterable<AddressEntity> addresses = addressRepository.findAllByUserDetails(userEntity);
    for(AddressEntity addressEntity:addresses) {
      returnValue.add(modelMapper.map(addressEntity, AddressDTO.class));
    }

    return returnValue;
  }

  @Override
  public AddressDTO getAddressSingle(String AddressId) {
    AddressDTO returnValue = null;
    AddressEntity addressEntity = addressRepository.findAllByAddressId(AddressId);

    if(addressEntity != null) {
      returnValue = new ModelMapper().map(addressEntity, AddressDTO.class);
    }

    return returnValue;
  }
}
