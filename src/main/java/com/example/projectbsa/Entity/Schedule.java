package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table(name="Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSchedule;

    @Column(name="id")
    private long id;

    @Column(name="[time]")
    private String time;

    @Column(name="order_seats")
    private int orderSeats;

    @Column(name="date_seats")
    private String dateSeats;

    @Column(name="place")
    private String place;

    @Column(name="[date]")
    private String date;

    @Column(name="comment")
    private String comment;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="total_money")
    private int total_money;

    @Column(name="voucher")
    private String voucher;

    @Column(name="[check]")
    private String check;

    public long getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(long idSchedule) {
        this.idSchedule = idSchedule;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOrderSeats() {
        return orderSeats;
    }

    public void setOrderSeats(int orderSeats) {
        this.orderSeats = orderSeats;
    }

    public String getDateSeats() {
        return dateSeats;
    }

    public void setDateSeats(String dateSeats) {
        this.dateSeats = dateSeats;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getTotal_money() {
        return total_money;
    }

    public void setTotal_money(int total_money) {
        this.total_money = total_money;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public Schedule() {
    }

    public Schedule(long idSchedule, long id, String time, int orderSeats, String dateSeats, String place, String date, String comment, boolean enabled, int total_money, String voucher, String check) {
        this.idSchedule = idSchedule;
        this.id = id;
        this.time = time;
        this.orderSeats = orderSeats;
        this.dateSeats = dateSeats;
        this.place = place;
        this.date = date;
        this.comment = comment;
        this.enabled = enabled;
        this.total_money = total_money;
        this.voucher = voucher;
        this.check = check;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "idSchedule=" + idSchedule +
                ", id=" + id +
                ", time='" + time + '\'' +
                ", orderSeats=" + orderSeats +
                ", dateSeats='" + dateSeats + '\'' +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                ", enabled=" + enabled +
                ", total_money=" + total_money +
                ", voucher='" + voucher + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
