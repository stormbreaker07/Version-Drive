package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.models.ResetPasswordModel;
import com.example.versionDriver.models.SignInUserObject;
import com.example.versionDriver.models.User;
import com.example.versionDriver.services.ResetPasswordService;
import com.example.versionDriver.services.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
public class UserVerification {

    //autowiring userRegister service
    @Autowired
    private UserVerificationService userRegister;
    @Autowired
    private ResetPasswordService resetPassword;

    // user login "/login"
    //user register "/register"
    //user reset Password "/reset_password"

    @PostMapping("/register")
    public String userRegister(@RequestBody User newUser) throws Exception {
        UserEntity userEntity = new UserEntity(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
        userRegister.registerUser(userEntity);
        return "Ok i got this.!";
    }

    @PostMapping("/signIn")
    public String verifyUser(@RequestBody SignInUserObject signInDataObject) {
        return userRegister.verifyUser(signInDataObject);
    }

    @PostMapping("/reset-password")
    public String updatePassword(@RequestBody ResetPasswordModel resetPasswordModel) {
        return resetPassword.resetPassword(resetPasswordModel);

    }

}
