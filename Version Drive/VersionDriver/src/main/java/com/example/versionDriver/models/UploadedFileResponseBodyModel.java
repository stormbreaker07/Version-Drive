package com.example.versionDriver.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class UploadedFileResponseBodyModel {

    @Getter @Setter private Long file_id;
    @Getter @Setter private String fileName;
    @Getter @Setter private String FileVersion;
    @Getter @Setter private Timestamp timestamp;

    public UploadedFileResponseBodyModel(Long file_id, String fileName, String fileVersion, Timestamp timestamp) {
        this.file_id = file_id;
        this.fileName = fileName;
        FileVersion = fileVersion;
        this.timestamp = timestamp;
    }
}
