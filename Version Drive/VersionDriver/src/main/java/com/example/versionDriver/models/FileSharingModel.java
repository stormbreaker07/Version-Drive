package com.example.versionDriver.models;
import lombok.Getter;
import lombok.Setter;

public class FileSharingModel {


    @Getter @Setter private String fileId;
    @Getter @Setter private String email;
    @Setter @Getter private String permission;
    @Getter @Setter private String ownerId;



    public FileSharingModel(String fileId , String email, String permission , String ownerId) {
        this.email = email;
        this.fileId = fileId;
        this.permission = permission;
        this.ownerId = ownerId;
    }


    public String toString() {
        return (email + " " + fileId + " " + permission + " " + ownerId );
    }

}
