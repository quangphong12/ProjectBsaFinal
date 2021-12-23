package com.example.projectbsa.Entity;

import javax.persistence.*;

@Entity
@Table(name="Voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="id_user")
    private long id_user;

    @Column(name="VC10K")
    private int VC10K;

    @Column(name="VC20K")
    private int VC20K;

    @Column(name="VCG5P")
    private int VCG5P;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public int getVC10K() {
        return VC10K;
    }

    public void setVC10K(int VC10K) {
        this.VC10K = VC10K;
    }

    public int getVC20K() {
        return VC20K;
    }

    public void setVC20K(int VC20K) {
        this.VC20K = VC20K;
    }

    public int getVCG5P() {
        return VCG5P;
    }

    public void setVCG5P(int VCG5P) {
        this.VCG5P = VCG5P;
    }

    public Voucher() {
    }

    public Voucher(long id, long id_user, int VC10K, int VC20K, int VCG5P) {
        this.id = id;
        this.id_user = id_user;
        this.VC10K = VC10K;
        this.VC20K = VC20K;
        this.VCG5P = VCG5P;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", VC10K=" + VC10K +
                ", VC20K=" + VC20K +
                ", VCG5P=" + VCG5P +
                '}';
    }
}
