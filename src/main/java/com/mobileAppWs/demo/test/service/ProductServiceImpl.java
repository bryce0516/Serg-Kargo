package com.mobileAppWs.demo.test.service;

import com.mobileAppWs.demo.test.Repository.ProductResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductResp productResp;

  @Override
  public ArrayList<ContractList> getNewProductList(String certno, String functrlno) {

    ArrayList<ContractList> newList = new ArrayList<>();
    return newList = productResp.findNewProductList(certno, functrlno);
  }
}
