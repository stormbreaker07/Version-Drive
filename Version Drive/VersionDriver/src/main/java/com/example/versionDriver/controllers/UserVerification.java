package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.exceptions.GenericException;
import com.example.versionDriver.models.ResetPasswordModel;
import com.example.versionDriver.models.SignInResponseModel;
import com.example.versionDriver.models.SignInUserObject;
import com.example.versionDriver.models.User;
import com.example.versionDriver.services.ResetPasswordService;
import com.example.versionDriver.services.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
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
    public ResponseEntity<String> userRegister(@RequestBody User newUser) throws Exception {
        try {
            UserEntity userEntity = new UserEntity(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
            userRegister.registerUser(userEntity);
            return ResponseEntity.ok().body("user successfully registered");
        }
        catch(GenericException ex) {
            if(ex.getMessage().equals("user already exist in database")) {
                return ResponseEntity.ok().body("Already Registered email id");
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("some Internal error occurs");
            }
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseModel> verifyUser(@RequestBody SignInUserObject signInDataObject) {
        UserEntity user =  userRegister.verifyUser(signInDataObject);
        SignInResponseModel responseBody = new SignInResponseModel(user.getId() , user.getFirstName() , user.getLastName(),user.getEmail());
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String > updatePassword(@RequestBody ResetPasswordModel resetPasswordModel) {
        String responseMessage =  resetPassword.resetPassword(resetPasswordModel);
        return ResponseEntity.ok().body(responseMessage);
    }

}
