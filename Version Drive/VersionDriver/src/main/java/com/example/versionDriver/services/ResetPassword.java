package com.example.versionDriver.services;

import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.models.ResetPasswordModel;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ResetPassword {

    @Autowired
    private RegisterUser usersInfoRepository;

    public String resetPassword(ResetPasswordModel newPasswordInfo) {
        ArrayList<UserEntity> allUsers = (ArrayList<UserEntity>) usersInfoRepository.findAll();
            Boolean userFound = false;
            String message = "";
            for(UserEntity user : allUsers) {
                if(user.getEmail().equals(newPasswordInfo.getEmail())) {
                    if (user.getPassword().equals(newPasswordInfo.getCurrentPassword())) {
                        user.setPassword(newPasswordInfo.getNewPassword());
                        usersInfoRepository.save(user);
                            message = "user Verified And Password updated";
                    } else {
                        message = "password mismatch and operation is a failure";
                    }
                    userFound = true;
                    break;
                }
            }

            if(userFound == false) {
                message = "sorry wrong email id and operation is a failure";
            }
    return message;
    }


    public String updatePassword(ResetPasswordModel newPasswordInfo) {
        Optional<UserEntity> userEntityOptional = usersInfoRepository.findUserByEmailAndPassword(newPasswordInfo.getEmail(), newPasswordInfo.getCurrentPassword());
        UserEntity user ;
        if(!userEntityOptional.isPresent()) {
            return "no such user";
        }else {
            user = userEntityOptional.get();
            user.setPassword(newPasswordInfo.getNewPassword());
            usersInfoRepository.save(user);
        }
        return "operation is a proper success";
    }

}
