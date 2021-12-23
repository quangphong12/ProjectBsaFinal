package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table (name="Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDriver;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="phone_driver")
    private String phoneDriver;

    @Column(name="experiences")
    private String experiences;

    public long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(long idDriver) {
        this.idDriver = idDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneDriver() {
        return phoneDriver;
    }

    public void setPhoneDriver(String phoneDriver) {
        this.phoneDriver = phoneDriver;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public Driver() {
    }

    public Driver(long idDriver, String name, int age, String gender, String phoneDriver, String experiences) {
        this.idDriver = idDriver;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phoneDriver = phoneDriver;
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "idDriver=" + idDriver +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phoneDriver='" + phoneDriver + '\'' +
                ", experiences='" + experiences + '\'' +
                '}';
    }
}
