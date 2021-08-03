package com.example.versionDriver.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import javax.persistence.*;


@Entity
@Table(name = "user_accounts")
public class UserEntity<List> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter private Long id;

    @Column(name = "first_name")
    @Getter @Setter private String firstName;

    @Column(name = "last_name")
    @Getter @Setter private String lastName;

    @Column(name = "email_id")
    @Getter @Setter private String email;

    @Column(name = "password")
    @Getter @Setter private String password;

    @OneToMany(mappedBy = "file_owner" , cascade = CascadeType.ALL)
    private java.util.List<UploadedFile> uploadedFileList;

    public UserEntity(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void add(UploadedFile newUploadFile) {
        if(uploadedFileList == null) {
            uploadedFileList = new ArrayList<>();
        }
        uploadedFileList.add(newUploadFile);
        newUploadFile.setFile_owner(this);
    }

    public java.util.List<UploadedFile> getUploadedFileList() {
        return uploadedFileList;
    }

    public void setUploadedFileList(java.util.List<UploadedFile> uploadedFileList) {
        this.uploadedFileList = uploadedFileList;
    }

    public UserEntity() {

    }

}
