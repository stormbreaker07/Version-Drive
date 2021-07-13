package com.example.versionDriver.services;

import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.exceptions.GenericException;
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
            throw new GenericException("user already exist in database");
        }

    }

    public UserEntity verifyUser(SignInUserObject signInUserData) throws GenericException {
        Boolean userFound = false;
        String message = "";
        Optional<UserEntity> user = registerUser.findUserByEmail(signInUserData.getEmail());
        if(user.isPresent()) {
            userFound = true;
            user = registerUser.findUserByEmailAndPassword(signInUserData.getEmail() , signInUserData.getPassword());
            if(user.isPresent()) {
                return user.get();
            }
            else {
                throw new GenericException("incorrect password");
            }

        }
        else {
            throw new GenericException("no user with this email id is register till now");
        }
    }


    public Boolean verifyUserById(String user_id) {
        Boolean result = false;
        Optional<UserEntity> user = registerUser.findById(Long.parseLong(user_id));

        if(user.isPresent()) {
            result = true;
        }
        return result;
    }
}
