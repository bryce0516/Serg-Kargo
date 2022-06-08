package com.mobileAppWs.demo.controllers;


import com.mobileAppWs.demo.models.request.MobileCheckRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class MobileCheckController {
    public static final Logger logger = LoggerFactory.getLogger(MobileCheckController.class);
    @PostMapping(
      consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public String checkMobile(@RequestBody MobileCheckRequestModel mobileDetails) throws Exception {
        logger.info("file ==== > " + mobileDetails);
        return "success";
    }




}
