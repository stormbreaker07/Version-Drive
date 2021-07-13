package com.example.versionDriver.services;


import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.repositories.UserRequestedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRequestedFileService {

    /***
     * save user_id file_id of the file that is requested to load and purpose to user_requested_file table
     */
    @Autowired
    private UserRequestedFileRepository userRequestedFileRepository;


    /***
     * addUser method add ownerId with fileid that is shared between owner and user
     * and also store user_id that requested for the file
     * @param file_id
     * @param user_id
     * @param owner_id
     * @param purpose
     */
    public void addUser(String file_id , String user_id ,String owner_id , String purpose ) {

        UserRequestedFile userRequestedFile = new UserRequestedFile(Long.parseLong(user_id) , Long.parseLong(file_id) ,Long.parseLong(owner_id) , purpose);
        userRequestedFileRepository.save(userRequestedFile);
    }


    /***
     * getAllSharedFIle method filter out the file you shared as the owner of the file to other users
     * reutrn an arraylist of all the requested
     * @param owner_id
     * @return
     */
    public List<UserRequestedFile> getAllSharedFile(String owner_id) {
        try {
            Optional<List<UserRequestedFile>> files = userRequestedFileRepository.findFilesByownerId(Long.parseLong(owner_id));
            if (files.isPresent()) {
                List<UserRequestedFile> existingFiles = files.get();
                existingFiles.forEach((n) -> System.out.println(n));
                return existingFiles;
            } else {
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     *getAllSharedFIle method filter out the file you requested to access as the user of the file to other users
     *reutrn an arraylist of all the requested
     * @param user_id
     * @return
     */
    public List<UserRequestedFile> getAllRequestedFile(String user_id) {
            List<UserRequestedFile> existingFiles = userRequestedFileRepository.findFilesByUserId(Long.parseLong(user_id));
                return existingFiles;

    }


}
