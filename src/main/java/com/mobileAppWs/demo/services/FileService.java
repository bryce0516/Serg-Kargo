package com.mobileAppWs.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Value("{files.path}")
    private String filesPath;

    public Resource download(String filename) {

    }

}
