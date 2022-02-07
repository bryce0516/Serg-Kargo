package com.mobileAppWs.demo.test.Repository;

import com.mobileAppWs.demo.test.entity.ProductEntity;
import com.mobileAppWs.demo.test.service.ContractList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface ProductResp extends JpaRepository<ProductEntity, String> {

  @Query(name = "findNewProductList", nativeQuery = true)
  ArrayList<ContractList> findNewProductList(@Param("certno") String certno, @Param("functrlno") String functrlno);
}
