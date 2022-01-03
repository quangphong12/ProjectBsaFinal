package com.example.projectbsa.Service.Impl;

import com.example.projectbsa.Entity.Account;
import com.example.projectbsa.Repository.AccountRepository;
import com.example.projectbsa.Repository.UserRepository;
import com.example.projectbsa.Service.AccountService;
import com.example.projectbsa.Service.EmailSenderService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Optional;


@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void deleteAccount(String username) {
        accountRepository.deleteAccount(username);
    }
    
    @Override
    public boolean checkLogin(String username, String password) {
        Optional<Account> optionalAccount= accountRepository.findById(username);
        if(optionalAccount.isPresent() && optionalAccount.get().getPassword().equals(password) && optionalAccount.get().isEnabled()){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkRole(String username ) {
        Optional<Account> optionalAccount= accountRepository.findById(username);
        if(optionalAccount.get().getRole().equals("admin")){
            return true;
        }
        return false;
    }


    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.updateAccount(account.getUsername(),account.getPassword(),account.getVerificationCode());
    }

    @Override
    public boolean checkUP(Account account){
        Optional<Account> optionalAccount= accountRepository.findById(account.getUsername());
        if(optionalAccount.isPresent()){
            return false;
        }
        return true;
    }
    @Override
    public boolean checkUP1(Account account, String email){
        Optional<Account> optionalAccount= accountRepository.findById(account.getUsername());
        String emailCheck= userRepository.checkEmail(account.getUsername());
        if(optionalAccount.isPresent() && email.equals(emailCheck)){
            return false;
        }
        return true;
    }
    @Override
    public void sendVerificationEmail(Account account,
                                      String email ,
                                      String siteURL,
                                      String randomCode) throws MessagingException, UnsupportedEncodingException {

        String verifyURL =siteURL+ "/account/verify?code="+randomCode;
        String toAddress = email;
        String subject = "Please verify your registration";
        String senderName=" BSA Bus ";
        String content = "<p>Dear "+account.getUsername()+",</p>";
        content += "<p>Please click the link below to verify your registration:</p>";
        content += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        content+= "<p>Thank you,<br>BSA BUS</p>";
        content=content.replace("[[name]]",account.getUsername());
        MimeMessage message= javaMailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message);
        helper.setFrom("ngoquangphong1212@gmail.com",senderName);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content,true);
        javaMailSender.send(message);
    }

    @Override
    public void sendChangePassword(String username,
                                   String email,
                                   String siteURL,
                                   String randomCode) throws MessagingException, UnsupportedEncodingException {
        String verifyURL =siteURL+ "/account/verifyNewPass?code="+randomCode;
        String toAddress = email;
        String subject = "Please verify your registration";
        String senderName=" BSA Bus ";
        String content = "<p>Dear "+username+",</p>";
        content += "<p>Please click the link below to verify and create new password:</p>";
        content += "<h3><a href=\""+verifyURL+"\">VERIFY</a></h3>";
        content+= "<p>Thank you,<br>BSA BUS</p>";
        content=content.replace("[[name]]",username);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom("ngoquangphong1212@gmail.com",senderName);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(content,true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public boolean verify(String verificationCode) {
        Account account= accountRepository.findByVerificationCode(verificationCode);
        if(account.getUsername() == null ){
            return false;
        }else{
            account.setVerificationCode(null);
            account.setEnabled(true);
            saveAccount(account);
        }
        //System.out.println(account.toString());
        return true;
    }

}
