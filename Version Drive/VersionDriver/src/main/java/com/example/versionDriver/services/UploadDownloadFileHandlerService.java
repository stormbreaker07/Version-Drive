package com.example.versionDriver.services;

import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.exceptions.GenericException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadDownloadFileHandlerService {

    @Autowired
    private UserVerificationService userVerificationService;
    @Autowired
    private UploadedFileService uploadedFiles;
    @Autowired
    private UserRequestedFileService userRequestedFileService;


    /***
     * uploadFile method done 2 works first upload the file in the resource folder and second is store the file data in
     * database
     * it first check if a user is a valid user if user is present in the database who is requesting for storing the
     * file then it confirm it and pass the process to next step.
     * now it increase the version value that means a new version and
     * after that file will be uploaded on the resource folder
     * now the new file is store in database and
     * update the one to many relation between user and files
     * @param file
     * @param owner_id
     * @param version
     * @return
     * @throws GenericException
     */
    public Boolean uploadFile(MultipartFile file, String owner_id, String version) throws GenericException {

        Boolean isFileUploaded = false;

        Boolean result = userVerificationService.verifyUserById(owner_id);
        if (result == false) {
            throw new GenericException("user id is wrong please send a currect user id ");
        }
        Long ver = Long.parseLong(version);
        ver++;

        String path = "/home/stormbreaker/Study/Version Drive/Version Drive/VersionDriver/src/main/resources/static/uploads"
                + File.separator + String.valueOf(ver) + "-" + file.getOriginalFilename();
        try {
            Path uploadPath = Paths.get(path);
            Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
            isFileUploaded = true;
            uploadedFiles.saveNewFileInDatabase(file.getOriginalFilename(), owner_id, String.valueOf(ver));
        } catch (Exception e) {
            throw new GenericException("unable to upload a file , please try again");
        }

        return isFileUploaded;
    }


    /***
     * LoadFile method matches that the owner id match with the owner id of the file and then
     * It return a inputstream of the requested file
     * Store the data of the user_id and file_id in database to make record who access the file.
     * and for what purpose
     * @param file_id
     * @param owner_id
     * @param user_id
     * @param purpose
     * @return
     * @throws GenericException
     */
    public InputStream loadFile(String file_id , String owner_id, String user_id,String purpose) throws GenericException{
         UploadedFile existingFile = uploadedFiles.getFileById(file_id);
        if(existingFile!=null) {
            System.out.println(existingFile.getFile_owner().getId() + owner_id);
             if(!String.valueOf(existingFile.getFile_owner().getId()).equals(owner_id)) {
                 throw new GenericException("file id" + file_id + "dont belong to the same owner" + owner_id);
             }
            InputStream resource = null;
            try {
                System.out.println("src/main/resources/static/uploads/"
                        + existingFile.getFileVersion() + "-" + existingFile.getFileName());
                File initialFile = new File("src/main/resources/static/uploads/"
                        + existingFile.getFileVersion() + "-" + existingFile.getFileName());
                resource = new FileInputStream(initialFile);
                userRequestedFileService.addUser(user_id , file_id ,owner_id, purpose);
                return resource;
            } catch (Exception e) {
                throw new GenericException("sorry not able to load file now , try again later");
            }
        }
        else {
            throw new GenericException("sorry file on this id doesnt exist...!");
        }
    }

}
