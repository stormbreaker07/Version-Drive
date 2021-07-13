package com.example.versionDriver.controllers;


import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.models.ResetPasswordModel;
import com.example.versionDriver.models.SignInResponseModel;
import com.example.versionDriver.models.SignInUserObject;
import com.example.versionDriver.models.User;
import com.example.versionDriver.services.ResetPasswordService;
import com.example.versionDriver.services.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> userRegister(@RequestBody User newUser) throws Exception {
        UserEntity userEntity = new UserEntity(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword());
        userRegister.registerUser(userEntity);
        return ResponseEntity.ok().body("user successfully registered");
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponseModel> verifyUser(@RequestBody SignInUserObject signInDataObject) {
        UserEntity user =  userRegister.verifyUser(signInDataObject);
        SignInResponseModel responseBody = new SignInResponseModel(user.getId() , user.getFirstName() , user.getLastName());
        return ResponseEntity.ok().body(responseBody);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String > updatePassword(@RequestBody ResetPasswordModel resetPasswordModel) {
        String responseMessage =  resetPassword.resetPassword(resetPasswordModel);
        return ResponseEntity.ok().body(responseMessage);
    }

}
