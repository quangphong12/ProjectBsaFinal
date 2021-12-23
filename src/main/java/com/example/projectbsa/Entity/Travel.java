package com.example.projectbsa.Entity;

import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name="Travel")
public class Travel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTravel;

    @Column(name="id_bus")
    private long idBus;

    @Column(name="travel_time")
    private String travelTime;

    @Column(name="place")
    private String place;

    @Column(name="seat_ordered")
    private int seatOrder;

    @Column(name="seat_empty")
    private int seatEmpty;

    public int getIdTravel() {
        return idTravel;
    }

    public void setIdTravel(int idTravel) {
        this.idTravel = idTravel;
    }

    public long getIdBus() {
        return idBus;
    }

    public void setIdBus(long idBus) {
        this.idBus = idBus;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getSeatOrder() {
        return seatOrder;
    }

    public void setSeatOrder(int seatOrder) {
        this.seatOrder = seatOrder;
    }

    public int getSeatEmpty() {
        return seatEmpty;
    }

    public void setSeatEmpty(int seatEmpty) {
        this.seatEmpty = seatEmpty;
    }

    public Travel() {
    }

    public Travel(int idTravel, long idBus, String travelTime, String place, int seatOrder, int seatEmpty) {
        this.idTravel = idTravel;
        this.idBus = idBus;
        this.travelTime = travelTime;
        this.place = place;
        this.seatOrder = seatOrder;
        this.seatEmpty = seatEmpty;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "idTravel=" + idTravel +
                ", idBus=" + idBus +
                ", travelTime='" + travelTime + '\'' +
                ", place='" + place + '\'' +
                ", seatOrder=" + seatOrder +
                ", seatEmpty=" + seatEmpty +
                '}';
    }
}
