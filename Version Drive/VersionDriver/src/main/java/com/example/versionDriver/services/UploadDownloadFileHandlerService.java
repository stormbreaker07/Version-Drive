package com.example.versionDriver.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadDownloadFileHandlerService {


    private Path uploadPath ;


    public Boolean uploadFile(MultipartFile file) {

        Boolean isFileUploaded = false;
        String path = "/home/stormbreaker/Study/Version Drive/Version Drive/VersionDriver/src/main/resources/static/uploads"
                + File.separator + file.getOriginalFilename();
        try {
            uploadPath = Paths.get(path);
            Files.copy(file.getInputStream() , uploadPath , StandardCopyOption.REPLACE_EXISTING);
            isFileUploaded = true;
        }catch(Exception e) {
            e.printStackTrace();
        }

        return isFileUploaded;
    }

    public InputStream loadFile(String fileName){
        InputStream resource = null;
        try {
            File initialFile = new File("src/main/resources/static/uploads/" + fileName);
            resource = new FileInputStream(initialFile);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return resource;
    }


}
