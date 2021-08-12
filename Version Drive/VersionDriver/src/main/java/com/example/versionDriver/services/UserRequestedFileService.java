package com.example.versionDriver.services;


import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.exceptions.GenericException;
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
    @Autowired
    private UserVerificationService userVerificationService;


    /***
     * addUser method add ownerId with fileid that is shared between owner and user
     * and also store user_id that requested for the file
     * @param fileId
     * @param emailId
     * @param userId
     * @param purpose
     */
    public Boolean addUser(String fileId , String emailId ,String userId , String purpose ) throws GenericException {
        try {
            String recieverId = userVerificationService.getUserIdByEmailId(emailId);
            UserRequestedFile userRequestedFile = new UserRequestedFile(Long.parseLong(userId), Long.parseLong(recieverId), Long.parseLong(fileId), purpose);
            userRequestedFileRepository.save(userRequestedFile);
            return true;
        }
        catch(Exception ex) {
            throw new GenericException("action failure , Internal Server Error , please try again !");
        }
    }


    /***
     * getAllSharedFIle method filter out the file you shared as the owner of the file to other users
     * reutrn an arraylist of all the requested
     * @param owner_id
     * @return
     */
    public List<UserRequestedFile> getAllSharedFile(String owner_id) {
        try {
            Optional<List<UserRequestedFile>> files = userRequestedFileRepository.findFilesByownerId(Long.parseLong(owner_id), Long.parseLong(owner_id) );
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


    public List<UserRequestedFile> getFileBYFileId(Long userId) {
        List<UserRequestedFile> file = userRequestedFileRepository.findByFileID(userId);
        return file;
    }
}
