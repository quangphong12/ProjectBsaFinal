package com.example.projectbsa.Entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name="Bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idBus;

    @Column(name="id_driver")
    private long idDriver;

    @Column(name="license_plate_bus")
    private String licensePlateBus;

    @Column(name="name_bus")
    private String nameBus;

    @Column(name="seats")
    private int seats;

    @Column(name="color")
    private String color;

    public long getIdBus() {
        return idBus;
    }

    public void setIdBus(long idBus) {
        this.idBus = idBus;
    }

    public long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(long idDriver) {
        this.idDriver = idDriver;
    }

    public String getLicensePlateBus() {
        return licensePlateBus;
    }

    public void setLicensePlateBus(String licensePlateBus) {
        this.licensePlateBus = licensePlateBus;
    }

    public String getNameBus() {
        return nameBus;
    }

    public void setNameBus(String nameBus) {
        this.nameBus = nameBus;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Bus() {
    }

    public Bus(long idBus, long idDriver, String licensePlateBus, String nameBus, int seats, String color) {
        this.idBus = idBus;
        this.idDriver = idDriver;
        this.licensePlateBus = licensePlateBus;
        this.nameBus = nameBus;
        this.seats = seats;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "idBus=" + idBus +
                ", idDriver=" + idDriver +
                ", licensePlateBus='" + licensePlateBus + '\'' +
                ", nameBus='" + nameBus + '\'' +
                ", seats=" + seats +
                ", color='" + color + '\'' +
                '}';
    }
}
