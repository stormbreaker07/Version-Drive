package com.example.versionDriver.entities;

import javax.persistence.*;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.Instant;


@Entity
@Table(name = "uploaded_files")

public class UploadedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="file_id")
    private Long fileId;
    @Column(name ="file_name")
    private String fileName;
    @Column(name ="file_version")
    private String fileVersion;
    @Column(name ="timestamp")
    private Timestamp timestamp;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE , CascadeType.PERSIST , CascadeType.REFRESH})
    @JoinColumn(name = "file_owner_id")
    private UserEntity file_owner;

    public UserEntity getFile_owner() {
        return file_owner;
    }

    public void setFile_owner(UserEntity file_owner) {
        this.file_owner = file_owner;
    }

    public UploadedFile() {
        timestamp = Timestamp.from(Instant.now());
    }

    public UploadedFile( String fileName, String fileVersion) {
        this();
        this.fileName = fileName;
        this.fileVersion = fileVersion;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileVersion() {
        return fileVersion;
    }

    public void setFileVersion(String fileVersion) {
        this.fileVersion = fileVersion;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
