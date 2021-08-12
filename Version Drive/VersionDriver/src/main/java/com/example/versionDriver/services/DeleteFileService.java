package com.example.versionDriver.services;

import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.exceptions.GenericException;
import com.example.versionDriver.repositories.UploadedFileRepository;
import com.example.versionDriver.repositories.UserRequestedFileRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class DeleteFileService {

    @Autowired
    private UploadedFileRepository uploadedFiles;
    @Autowired
    private UserRequestedFileRepository userRequestedFileRepository;


    /***
     * deleteSingleFileAsRequestedUser delete the file if user have ever get the permission
     * from the owner or uploader of the file to delete the file
     * @param fileId id of the file requested to be deleted
     * @param userId id of user who want to delete the file
     * @throws IOException deleteFile method may cause IOException
     */
    public void deleteSingleFileAsRequestedUser(String fileId, String userId) throws IOException {
        UserRequestedFile temp = userRequestedFileRepository.findByFileIDAndUserId(Long.parseLong(fileId), Long.parseLong(userId));
        if (temp == null) {
            throw new GenericException("file not found !");
        }
        String purpose = temp.getPurpose();
        switch (purpose) {
            case "view":
                throw new GenericException("User have permission to View the file not to delete the file");
            case "download":
                throw new GenericException("user have permission to download the file not to delete the file");
            case "update":
                throw new GenericException("user have permission to upload a new version of this file but not to delete the file");
            case "delete":
                Optional<UploadedFile> file = uploadedFiles.findById(Long.parseLong(fileId));
                deleteFile(file, fileId);
                break;
        }
    }


    /**
     * deleteSingleFile method is capable to delete a file from resource as well as from the database
     * file is deleting from resources/static/uploads folder
     * and data from UploadedFileRepository.
     *
     * @param fileId  it is the id of file requested to be deleted
     * @param ownerId it to verify that the user give the permission to delete the file is owner of the file ,so it is owner_id
     * @throws IOException line 37 may throw IOException
     */
    public void deleteSingleFile(String fileId, String ownerId) throws IOException {
        Optional<UploadedFile> file = uploadedFiles.findById(Long.parseLong(fileId));
        if (file.isPresent()) {
            if (file.get().getFile_owner().getId().equals(Long.parseLong(ownerId))) {
                deleteFile(file, fileId);
            } else {
                deleteSingleFileAsRequestedUser(fileId, ownerId);
            }
        } else {
            throw new GenericException("there is no such file present with that id");
        }
    }

    /**
     *
     * @param file the uploadedFile entity object stored necessary data to delete the file
     * @param fileId the id of the file to be deleted
     * @throws IOException Files.delete may cause IOException
     */
    public void deleteFile(Optional<UploadedFile> file, String fileId) throws IOException {
        if(file.isPresent()) {
            UploadedFile existingFile = file.get();
            Path path = Paths.get("src/main/resources/static/uploads/"
                    + existingFile.getFileVersion() + "-" + existingFile.getFileName());
            Files.delete(path);
            deleteFileFromDataBase(fileId);
        }
    }

    /***
     * delete the file info from the database
     * @param fileId the file info requested to be deleted
     */
    public void deleteFileFromDataBase(String fileId) {
        uploadedFiles.deleteById(Long.parseLong(fileId));
        List<UserRequestedFile> files = userRequestedFileRepository.findByFileID(Long.parseLong(fileId));
        for(UserRequestedFile x : files ) {
            System.out.println("so its working");
            userRequestedFileRepository.delete(x);
        }
        //userRequestedFileRepository.deleteFilesByFileId(Long.parseLong(fileId));
    }


}
