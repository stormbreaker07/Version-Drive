package com.example.versionDriver.services;

import com.example.versionDriver.entities.UserEntity;
import com.example.versionDriver.exceptions.GenericException;
import com.example.versionDriver.models.ResetPasswordModel;
import com.example.versionDriver.repositories.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ResetPasswordService {

    @Autowired
    private RegisterUser usersInfoRepository;

    public String resetPassword(ResetPasswordModel newPasswordInfo) throws GenericException {
        ArrayList<UserEntity> allUsers = (ArrayList<UserEntity>) usersInfoRepository.findAll();
            Boolean userFound = false;
            String message = "";
            for(UserEntity user : allUsers) {
                if(user.getEmail().equals(newPasswordInfo.getEmail())) {
                    userFound = true;
                    if (user.getPassword().equals(newPasswordInfo.getCurrentPassword())) {
                        user.setPassword(newPasswordInfo.getNewPassword());
                        usersInfoRepository.save(user);
                            return "user Verified And Password updated";
                    } else {
                        throw new GenericException("password mismatch");
                    }
                }

            }
            if(!userFound) {
                throw new GenericException("sorry wrong email id and password");
            }
    return message;
    }


    public String updatePassword(ResetPasswordModel newPasswordInfo) {
        Optional<UserEntity> userEntityOptional = usersInfoRepository.findUserByEmailAndPassword(newPasswordInfo.getEmail(), newPasswordInfo.getCurrentPassword());
        UserEntity user ;
        if(userEntityOptional.isEmpty()) {
            return "no such user";
        }else {
            user = userEntityOptional.get();
            user.setPassword(newPasswordInfo.getNewPassword());
            usersInfoRepository.save(user);
        }
        return "operation is a proper success";
    }

}
