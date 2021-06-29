package com.example.versionDriver.entities;

import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;


@Entity
@Table(name = "user_accounts")
public class UserEntity<List> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
        private String lastName;
    @Column(name = "email_id")
    private String email;
    @Column(name = "password")
    private String password;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
