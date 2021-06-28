package com.example.versionDriver.controllers;


import com.example.versionDriver.services.UploadDownloadFileHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FileUploadDownloadController {

    @Autowired
    private UploadDownloadFileHandlerService uploadFileHandlerService;


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("send some reasonable or atleast some file");
            }
            Boolean result = uploadFileHandlerService.uploadFile(file);
            if(result) {
                return ResponseEntity.ok("i got the file");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sorry but we think it is internal server error please try again");
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) {

        try {
            InputStream resourceFile = uploadFileHandlerService.downloadFile(fileName);
            byte[] byteStream = resourceFile.readAllBytes();
            return ResponseEntity.ok()
                    .contentLength(byteStream.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(byteStream);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}
