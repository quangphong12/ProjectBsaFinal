package com.example.projectbsa.Service;

import com.example.projectbsa.Entity.Account;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public interface AccountService {

    boolean checkLogin(String username, String password);

    boolean checkRole(String username);

    void saveAccount(Account account);

    void updateAccount(Account account);

    boolean checkUP(Account account);

    boolean checkUP1(Account account, String email);

    void sendVerificationEmail(Account account, String email,String siteURL,String randomCode) throws MessagingException, UnsupportedEncodingException;

    void sendChangePassword(String username,String email,String siteURL,String randomCode) throws MessagingException, UnsupportedEncodingException;

    boolean verify(String verificationCode);

}
