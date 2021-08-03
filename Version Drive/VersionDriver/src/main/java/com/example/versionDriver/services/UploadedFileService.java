package com.example.versionDriver.services;

import com.example.versionDriver.entities.UploadedFile;
import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.repositories.RegisterUser;
import com.example.versionDriver.repositories.UploadedFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UploadedFileService {

    @Autowired
    private RegisterUser userDatabase;
    @Autowired
    private UploadedFileRepository uploadedFilerepository;


    /***
     * this method use to save file information in database and map it with its respected owner
     * version indicate which version of file is uploaded
     * @param fileName
     * @param owner_id
     * @return
     */
    public String saveNewFileInDatabase(String fileName, String owner_id, String version) {
        UploadedFile newFile = new UploadedFile(fileName, version);
        Optional<UserEntity> user = userDatabase.findById(Long.parseLong(owner_id));
        if (user.isPresent()) {
            UserEntity owner = user.get();
            owner.add(newFile);
            uploadedFilerepository.save(newFile);
            userDatabase.save(owner);
        }

        return String.valueOf(version);
    }

    public UploadedFile getFileById(String file_id) {
        Optional<UploadedFile> file = Optional.of(uploadedFilerepository.getById(Long.parseLong(file_id)));
        if (file.isPresent()) {
            UploadedFile existingFile = file.get();
            return existingFile;
        }
        return null;

    }



}
