package com.example.versionDriver.services;

import com.example.versionDriver.repositories.RegisterUser;
import com.example.versionDriver.repositories.UploadedFilerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteFileService {

    @Autowired
    private RegisterUser registerUser;
    @Autowired
    private UploadedFilerepository uploadedFiles;


}
