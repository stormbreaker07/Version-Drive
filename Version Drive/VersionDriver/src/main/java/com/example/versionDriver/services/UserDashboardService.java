package com.example.versionDriver.services;

import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDashboardService {

    @Autowired
    private RegisterUser registerUser;


    public ArrayList<UploadedFile> getAlluploadedFiles(String user_id) {
        Optional<UserEntity> user = registerUser.findById(Long.parseLong(user_id));
        if(user.isPresent()) {
            UserEntity tempUser = user.get();
            return (ArrayList<UploadedFile>) tempUser.getUploadedFileList();
        }
        else {
            return new ArrayList<>();
        }
    }


}
