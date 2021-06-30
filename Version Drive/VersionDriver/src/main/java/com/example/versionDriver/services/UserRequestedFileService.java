package com.example.versionDriver.services;


import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.repositories.UserRequestedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRequestedFileService {

    /***
     * save user_id file_id of the file that is requested to load and purpose to user_requested_file table
     */
    @Autowired
    private UserRequestedFileRepository userRequestedFileRepository;

    public void addUser(String file_id , String user_id ,String owner_id , String purpose ) {

        UserRequestedFile userRequestedFile = new UserRequestedFile(Long.parseLong(user_id) , Long.parseLong(file_id) ,Long.parseLong(owner_id) , purpose);
        userRequestedFileRepository.save(userRequestedFile);
    }

    public void getAllSharedFile(String owner_id) {
        userRequestedFileRepository.findFilesByownerId(owner_id);

    }

}
