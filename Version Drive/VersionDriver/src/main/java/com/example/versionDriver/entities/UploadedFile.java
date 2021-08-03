package com.example.versionDriver.entities;

import lombok.Getter;
import lombok.Setter;

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
    @Getter @Setter private Long fileId;
    @Column(name ="file_name")
    @Getter @Setter private String fileName;
    @Column(name ="file_version")
    @Getter @Setter private String fileVersion;
    @Column(name ="timestamp")
    @Getter @Setter private Timestamp timestamp;

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

}
