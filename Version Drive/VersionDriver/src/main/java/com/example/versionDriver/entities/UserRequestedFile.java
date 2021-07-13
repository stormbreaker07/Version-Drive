package com.example.versionDriver.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_requested_file")
public class UserRequestedFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter @Setter private Long id;

    @Column(name = "owner_id")
    @Getter @Setter private Long ownerId;

    @Column(name = "user_id")
    @Getter @Setter private Long userId;

    @Column(name = "file_id")
    @Getter @Setter private Long fileId;

    @Column(name = "purpose")
    @Getter @Setter private String purpose;

    public UserRequestedFile() {
    }

    public UserRequestedFile(Long ownerId, Long userId, Long fileId, String purpose) {
        this.ownerId = ownerId;
        this.userId = userId;
        this.fileId = fileId;
        this.purpose = purpose;
    }


    @Override
    public String toString() {
        return "UserRequestedFile{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", userId=" + userId +
                ", fileId=" + fileId +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}
