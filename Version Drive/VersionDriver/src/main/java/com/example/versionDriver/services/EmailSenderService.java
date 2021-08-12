package com.example.versionDriver.services;

import com.example.versionDriver.models.FileSharingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private UserVerificationService userVerificationService;
    @Autowired
    private UploadedFileService uploadedFileService;

    public void sendMail(FileSharingModel fileShareInfo) {
        String senderEmailId = userVerificationService.getEmailIdByUserId(Long.parseLong(fileShareInfo.getOwnerId()));
        String recieverEmailId = fileShareInfo.getEmail();
        String fileName = uploadedFileService.getFileNameById(fileShareInfo.getFileId());
        String subject = "A file is shared with you!";
        String text = "A file is shared with you by user having email id " + senderEmailId + " and file name is " + fileName
                + "with the maximum permission of '" + fileShareInfo.getPermission() + "' the file.";
        sendSimpleMail(recieverEmailId , subject , text);
    }


    public void sendSimpleMail(String to , String subject , String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@versiondrive.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
        System.out.println("message is sended");
    }


}
