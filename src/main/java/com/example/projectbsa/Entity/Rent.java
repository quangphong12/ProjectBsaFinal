package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table (name="Rent")
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rent;

    @Column(name="id")
    private long id;

    @Column(name="date_time_begin")
    private String dateTimeBegin;

    @Column(name="date_time_end")
    private String dateTimeEnd;

    @Column(name="place")
    private String place;

    @Column(name="city")
    private String city;

    @Column(name="[type]")
    private int type;

    @Column(name="car_company")
    private String carCompany;

    @Column(name="type_rent")
    private String typeRent;

    @Column(name="comment")
    private String comment;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="[check]")
    private String check;

    public long getId_rent() {
        return id_rent;
    }

    public void setId_rent(long id_rent) {
        this.id_rent = id_rent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateTimeBegin() {
        return dateTimeBegin;
    }

    public void setDateTimeBegin(String dateTimeBegin) {
        this.dateTimeBegin = dateTimeBegin;
    }

    public String getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(String dateTimeEnd) {
        this.dateTimeEnd = dateTimeEnd;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCarCompany() {
        return carCompany;
    }

    public void setCarCompany(String carCompany) {
        this.carCompany = carCompany;
    }

    public String getTypeRent() {
        return typeRent;
    }

    public void setTypeRent(String typeRent) {
        this.typeRent = typeRent;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Rent() {
    }

    public Rent(long id_rent, long id, String dateTimeBegin, String dateTimeEnd, String place, String city, int type, String carCompany, String typeRent, String comment, boolean enabled, String check) {
        this.id_rent = id_rent;
        this.id = id;
        this.dateTimeBegin = dateTimeBegin;
        this.dateTimeEnd = dateTimeEnd;
        this.place = place;
        this.city = city;
        this.type = type;
        this.carCompany = carCompany;
        this.typeRent = typeRent;
        this.comment = comment;
        this.enabled = enabled;
        this.check = check;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "id_rent=" + id_rent +
                ", id=" + id +
                ", dateTimeBegin='" + dateTimeBegin + '\'' +
                ", dateTimeEnd='" + dateTimeEnd + '\'' +
                ", place='" + place + '\'' +
                ", city='" + city + '\'' +
                ", type=" + type +
                ", carCompany='" + carCompany + '\'' +
                ", typeRent='" + typeRent + '\'' +
                ", comment='" + comment + '\'' +
                ", enabled=" + enabled +
                ", check='" + check + '\'' +
                '}';
    }
}
