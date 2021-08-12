package com.example.versionDriver.services;

import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.entities.UserRequestedFile;
import com.example.versionDriver.models.SharedFilesResponseModel;
import com.example.versionDriver.models.UploadedFileResponseBodyModel;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDashboardService {

    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private UserRequestedFileService userRequestedFileService;
    @Autowired
    private UploadedFileService uploadedFileService;
    @Autowired
    private UserVerificationService userVerificationService;

    /***
     * getALlUploadedFiles method return all the files data uploaded by the user
     * or say all the files having one user to many files relation in database
     * @param userId the user requested to get all files data uploaded by him/her
     * @return list of UploadedFileResponseModel this model contain some field that are important to recognise the files
     */
    public List<UploadedFileResponseBodyModel> getAllUploadedFiles(String userId) {
        Optional<UserEntity> user = registerUser.findById(Long.parseLong(userId));
        if(user.isPresent()) {
            UserEntity tempUser = user.get();
            List<UploadedFile> allFiles = tempUser.getUploadedFileList();
            List<UploadedFileResponseBodyModel> files = new ArrayList<>();
            for(UploadedFile x : allFiles) {
                UploadedFileResponseBodyModel temp = new UploadedFileResponseBodyModel(x.getFileId()
                        , x.getFileName(), x.getFileVersion() , x.getTimestamp());
                files.add(temp);
            }
         return files;
        }
        else {
            return null;
        }
    }

    /***
     * getAllSharedFile return all the file shared by user to some other user.
     * UserRequestedFile is an entity so we send data in entity format
     * @param userId user requested to get his/her shared files
     * @return list of UserRequestedFile that is shared by user
     */
    public List<SharedFilesResponseModel> getAllSharedFile(String userId) {
        List<UserRequestedFile> tempData = userRequestedFileService.getAllSharedFile(userId);
        List<SharedFilesResponseModel> sharedFiles = new ArrayList<>();

        for(UserRequestedFile x : tempData) {
            UploadedFile tempFile = uploadedFileService.getFileById(String.valueOf(x.getFileId()));
            String recieverEmailId = userVerificationService.getEmailIdByUserId(x.getUserId());
            sharedFiles.add(new SharedFilesResponseModel(x.getId() , x.getOwnerId() , recieverEmailId ,x.getPurpose(), tempFile));
        }

        return sharedFiles;
    }

//    /***
//     * getAllSharedFile return all the file requested by user to get some particular purppos
//     * from some other user.
//     * UserRequestedFile is an entity so we send data in entity format
//     * @param userId user requested to get the files data he/she get access from other user
//     * @return list of UserRequestedFile that he/she get access of.
//     */
//    public List<SharedFilesResponseModel> getAllRequestedFile(String userId) {
//        List<UserRequestedFile> tempData = userRequestedFileService.getAllRequestedFile(userId);
//        List<SharedFilesResponseModel> requestedFiles = new ArrayList<>();
//
//        for(UserRequestedFile x : tempData) {
//            UploadedFile tempFile = uploadedFileService.getFileById(String.valueOf(x.getFileId()));
//            requestedFiles.add(new SharedFilesResponseModel(x.getId() , x.getOwnerId() , x.getUserId() , tempFile));
//        }
//
//        return requestedFiles;
//    }


    /***
     * storageUsed method return the total storage of drive used till now by the user
     * @param userId the user requested to see how much storage is used till now
     * @return String of totalSize the double totalSize is cast in to string so that no changes can be made
     * @throws Exception majorly IOException can take place
     */
    public String storageUsed(String userId) throws Exception {
        List<UploadedFileResponseBodyModel> files = getAllUploadedFiles(userId);
        Double totalSize = 0D;
        for(UploadedFileResponseBodyModel x : files) {
            String fileName = x.getFileName();
            String fileVersion = x.getFileVersion();
            String filePath = "src/main/resources/static/uploads/"
                    + fileVersion + "-" + fileName;
            Path path = Paths.get(filePath);

            try {
                Long fileSize = Files.size(path);
                String temp = String.valueOf(fileSize);
                Double fileSizeInMB = Double.parseDouble(temp)/(1024*1024);
                totalSize += fileSizeInMB;
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        return String.valueOf(totalSize);
    }

}
