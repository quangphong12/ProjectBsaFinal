package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table(name="Account")
public class Account {
    @Id
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="role")
    private String role;

    @Column(name="verification_code")
    private String verificationCode;

    @Column(name="enabled")
    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Account() {
    }

    public Account(String username, String password, String role, String verificationCode, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.verificationCode = verificationCode;
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
