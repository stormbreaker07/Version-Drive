package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dashboard")
public class UserDashBoardController {



    /***
     * it will show all the files uploaded the user
     */
    @GetMapping("{user_id}/all-files")
    public ResponseEntity<ArrayList<UploadedFile>> allFiles() {

    }


    @GetMapping("{user_id}/shared-files")
    public void sharedFiles() {

    }

    @GetMapping("{user_id}/requested-files")
    public void requestedFiles() {

    }

}
