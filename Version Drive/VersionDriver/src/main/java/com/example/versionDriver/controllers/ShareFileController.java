package com.example.versionDriver.controllers;


import com.example.versionDriver.models.FileSharingModel;
import com.example.versionDriver.services.EmailSenderService;
import com.example.versionDriver.services.UserRequestedFileService;
import com.example.versionDriver.services.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("files")
public class ShareFileController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserVerificationService userVerificationService;
    @Autowired
    private UserRequestedFileService userRequestedFileService;


    @GetMapping("verify-email/{emailId}")
    public ResponseEntity<String> verifyEmail(@PathVariable String emailId) {
        try {
            boolean result = userVerificationService.findByEmailId(emailId);

            if (result) {
                return ResponseEntity.ok().body("Verified");
            }
            return ResponseEntity.ok().body("User with "+ emailId + " doesn't exist");
        }
        catch(Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failure");
        }
    }

    @PostMapping("{userId}/share-file")
    public ResponseEntity<String> fileShareWithOtherUsers(@RequestBody FileSharingModel fileSharingInfo) {

        System.out.println(fileSharingInfo);
        try {
            Boolean result = userRequestedFileService.addUser(fileSharingInfo.getFileId(), fileSharingInfo.getEmail(), fileSharingInfo.getOwnerId(), fileSharingInfo.getPermission());
            if(result) {
                emailSenderService.sendMail(fileSharingInfo);
                return ResponseEntity.ok().body("Action Successfull");
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("please try again !");
            }
        }
        catch(Exception ex) {
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

}
