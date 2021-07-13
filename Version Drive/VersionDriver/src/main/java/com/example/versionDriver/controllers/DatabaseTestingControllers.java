package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.repositories.RegisterUser;
import com.example.versionDriver.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/testing")
public class DatabaseTestingControllers {

    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private UploadedFileRepository uploadFiles;


    @GetMapping("/addData")
    public String StoreData() {

        UploadedFile newFile = new UploadedFile("naruto.jpg" , "2.0");
        UserEntity userEntity = new UserEntity("tanuj" , "yadav" , "woerwueu@gmail.com" , "123567567");
        userEntity.add(newFile);
        uploadFiles.save(newFile);
        registerUser.save(userEntity);
        return "ok successfull";
    }

    @GetMapping("/user/{id}")
    public String storeAgain(@PathVariable String id) {
        UploadedFile newFile = new UploadedFile("k-project.jpg" , "1.0");
        Optional<UserEntity> user = registerUser.findById( Long.parseLong(id));

        if(user.isPresent()) {
            UserEntity oldUser = user.get();
            oldUser.add(newFile);
            uploadFiles.save(newFile);
            registerUser.save(oldUser);
            return "operation successfully";
        }
        return "some hinderance took place";
    }


}
