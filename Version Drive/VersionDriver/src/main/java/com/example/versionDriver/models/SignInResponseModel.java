package com.example.versionDriver.models;

import lombok.Getter;
import lombok.Setter;

public class SignInResponseModel {
    @Getter @Setter private Long id;
    @Getter @Setter private String firstName;
    @Getter @Setter private String lastName;

    public SignInResponseModel(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
