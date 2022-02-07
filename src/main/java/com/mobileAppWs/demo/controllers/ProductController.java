package com.mobileAppWs.demo.controllers;


import com.mobileAppWs.demo.test.model.CommonResult;
import com.mobileAppWs.demo.test.service.ResponseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ProductController {
  private final ResponseService responseService;

  public ProductController(ResponseService responseService) {
    this.responseService = responseService;
  }

 // @GetMapping(path="/product/new")
 // public CommonResult getNewProductListData() {
 //   return responseService.getListResult()
 //  };
   @GetMapping(path="/product/new")
   public String getNewProductListData() {
     return "helloworld";
    };
}
