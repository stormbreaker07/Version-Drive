package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.exceptions.GenericException;
import com.example.versionDriver.models.SharedFilesResponseModel;
import com.example.versionDriver.models.UploadedFileResponseBodyModel;
import com.example.versionDriver.services.DeleteFileService;
import com.example.versionDriver.services.UserDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("dashboard")
public class UserDashBoardController {


    @Autowired
    private UserDashboardService userDashboardService;
    @Autowired
    private DeleteFileService deleteFileService;

    /***
     * it will show all the files uploaded the user
     */
    @GetMapping("{user_id}/all-files")
    public ResponseEntity<List<UploadedFileResponseBodyModel>> allFiles(@PathVariable String user_id) {
        return ResponseEntity.ok().body(userDashboardService.getAllUploadedFiles(user_id));
    }


    @GetMapping("{user_id}/shared-files")
    public ResponseEntity<List<SharedFilesResponseModel>> sharedFiles(@PathVariable String user_id) {
        return ResponseEntity.ok().body(userDashboardService.getAllSharedFile(user_id));
    }


//    @GetMapping("{user_id}/requested-files")
//    public ResponseEntity<List<SharedFilesResponseModel>> requestedFiles(@PathVariable String user_id) {
//        return ResponseEntity.ok().body(userDashboardService.getAllRequestedFile(user_id));
//    }


    @GetMapping("{user_id}/delete-file/{file_id}")
    public ResponseEntity<String> deleteRequestedFile(@PathVariable String user_id , @PathVariable String file_id) {
        try {
            deleteFileService.deleteSingleFile(file_id, user_id);
            return ResponseEntity.ok().body("SUCCESS");
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file may not be exist");
        }
    }


    /***
     * controller return the total storage used by the user having user id given in Path variable
     * @param userId id of the user requested to get the data related to storage used
     * @return return the string of total storage used
     */
    @GetMapping("storage-used/{userId}")
    public ResponseEntity<String> storageUsed(@PathVariable String userId) {
        try {
            String storageUse = userDashboardService.storageUsed(userId);
            return ResponseEntity.ok().body(storageUse);
        }catch(Exception e) {
            throw new GenericException(e.getMessage());
        }
    }



}
