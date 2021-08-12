package com.example.versionDriver.models;

import com.example.versionDriver.entities.UploadedFile;
import lombok.Getter;
import lombok.Setter;


public class SharedFilesResponseModel {

    @Getter @Setter private Long ownerId;
    @Getter @Setter private String userEmail;
    @Getter @Setter private Long id;
    @Getter @Setter private String purpose;
    @Getter @Setter private UploadedFileResponseBodyModel fileInfo;

    public SharedFilesResponseModel(Long id , Long ownerId , String userEmail ,String purpose, UploadedFile file) {
        this.ownerId = ownerId;
        this.id = id;
        this.purpose = purpose;
        this.userEmail = userEmail;
        UploadedFileResponseBodyModel fileInfo = new UploadedFileResponseBodyModel(file.getFileId(),file.getFileName(),file.getFileVersion(),file.getTimestamp());
        this.fileInfo = fileInfo;
    }




}
