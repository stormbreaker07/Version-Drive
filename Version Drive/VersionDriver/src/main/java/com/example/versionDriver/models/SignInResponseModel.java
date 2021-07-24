package com.example.versionDriver.models;

import lombok.Getter;
import lombok.Setter;

public class SignInResponseModel {
    @Getter @Setter private Long id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;
    @Getter @Setter private String email;

    public SignInResponseModel(Long id, String firstName, String lastName,String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
