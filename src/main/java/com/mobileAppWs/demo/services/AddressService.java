package com.mobileAppWs.demo.services;

import com.mobileAppWs.demo.common.dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
  List<AddressDTO> getAddress(String userId);
}
