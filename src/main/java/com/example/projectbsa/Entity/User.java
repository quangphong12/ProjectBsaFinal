package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table(name="[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="username")
    private String username;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @Column(name="point")
    private int point;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="[address]")
    private String address;

    @Column(name="date_of_birth")
    private String date_of_birth;

    @Column(name="CMND")
    private long CMND;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public long getCMND() {
        return CMND;
    }

    public void setCMND(long CMND) {
        this.CMND = CMND;
    }

    public User() {
    }

    public User(long id, String username, String name, String gender, int point, String phone, String email, String address, String date_of_birth, long CMND) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.point = point;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.date_of_birth = date_of_birth;
        this.CMND = CMND;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", point=" + point +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", CMND=" + CMND +
                '}';
    }
}
