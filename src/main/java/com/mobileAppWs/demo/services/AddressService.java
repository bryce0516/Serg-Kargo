package com.mobileAppWs.demo.services;

import com.mobileAppWs.demo.common.dto.AddressDTO;

import java.util.List;

public interface AddressService {
  List<AddressDTO> getAddress(String userId);
}
