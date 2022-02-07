package com.mobileAppWs.demo.controllers;


import com.mobileAppWs.demo.test.model.CommonResult;
import com.mobileAppWs.demo.test.service.ProductServiceImpl;
import com.mobileAppWs.demo.test.service.ResponseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ProductController {
  private final ProductServiceImpl productServiceImpl;
  private final ResponseService responseService;

  public ProductController(ProductServiceImpl productServiceImpl, ResponseService responseService) {
    this.productServiceImpl = productServiceImpl;
    this.responseService = responseService;
  }

  @GetMapping(path="/product/new",
          produces = {MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE}
  )
  public CommonResult getNewProductListData(
          @RequestParam(value = "certno") String certno,
          @RequestParam(value = "functrlno") String functrlno
  ) {
    return responseService.getSingleResult(productServiceImpl.getNewProductList(certno,functrlno ));
  };

//   @GetMapping(path="/product/new")
//   public String getNewProductListData() {
//     return "helloworld";
//    };
}
