package com.mobileAppWs.demo.services;

import com.mobileAppWs.demo.controllers.FileController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {
    public static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${files.path}")
    private String filesPath;

    public Resource download(String filename) {
        try{
            Path file = Paths.get(filesPath).resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            logger.info("file ==== > " + file);
            logger.info("resource ==== > " + resource);
            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        }catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
