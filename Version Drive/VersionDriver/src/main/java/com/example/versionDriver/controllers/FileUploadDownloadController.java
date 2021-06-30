package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.services.UploadDownloadFileHandlerService;
import com.example.versionDriver.services.UploadedFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;

@RestController
@RequestMapping("/files")
public class FileUploadDownloadController {

    @Autowired
    private UploadedFileService uploadedFiles;
    @Autowired
    private UploadDownloadFileHandlerService uploadFileHandlerService;


    @PostMapping("{owner_id}/upload/{version}")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String owner_id, @PathVariable String version) {

        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("send some reasonable or atleast some file");
            }

            Boolean result = uploadFileHandlerService.uploadFile(file, owner_id, version);
            if (result) {
                return ResponseEntity.ok("the file is uploaded");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body("sorry but we think it is internal server error please try again");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("{user_id}/download/{file_id}/{owner_id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String file_id ,@PathVariable String owner_id , @PathVariable String user_id ) {

        try {
            InputStream resourceFile = uploadFileHandlerService.loadFile(file_id , owner_id,user_id,"download");
            byte[] byteStream = resourceFile.readAllBytes();
            UploadedFile file = uploadedFiles.getFileById(file_id);
            return ResponseEntity.ok()
                    .contentLength(byteStream.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(byteStream);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping("{user_id}/load/{file_id}/{owner_id}")
    public ResponseEntity<byte[]> loadFile(@PathVariable String file_id ,@PathVariable String owner_id , @PathVariable String user_id) {


        try {
            InputStream resourceFile = uploadFileHandlerService.loadFile(file_id , owner_id,user_id,"view");
            byte[] byteStream = resourceFile.readAllBytes();
            UploadedFile file = uploadedFiles.getFileById(file_id);
            return ResponseEntity.ok()
                    .contentLength(byteStream.length)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
                    .body(byteStream);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
