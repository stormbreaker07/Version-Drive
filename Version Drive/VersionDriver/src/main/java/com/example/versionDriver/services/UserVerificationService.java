package com.example.versionDriver.services;

import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.models.SignInUserObject;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UserVerificationService {

    //registerUser repository store data in
    @Autowired
    private RegisterUser registerUser;

    /**
     * the new register user is registered.
     *
     *
     */
    public void registerUser(UserEntity userEntity) throws Exception {
        Boolean userFound = false;
        Optional<UserEntity> user = registerUser.findUserByEmail(userEntity.getEmail());
        if(!user.isPresent()) {
            registerUser.save(userEntity);
        }
        else {
            userFound = true;
        }

        if(userFound) {
            throw new Exception("user already exist in database");
        }

    }

    public String verifyUser(SignInUserObject signInUserData) {
        Boolean userFound = false;
        String message = "";
        Optional<UserEntity> user = registerUser.findUserByEmail(signInUserData.getEmail());
        if(user.isPresent()) {
            user = registerUser.findUserByEmailAndPassword(signInUserData.getEmail() , signInUserData.getPassword());
            if(user.isPresent()) {
                message = "operation is a success user verified";
            }
            else {
                message = "incorrect password";
            }
            userFound = true;
        }
        else {
            message = "no user with this email id is register till now";
        }

        if(userFound == false) {
            message = "user is not registered yet";

        }

        return message;
    }

}
