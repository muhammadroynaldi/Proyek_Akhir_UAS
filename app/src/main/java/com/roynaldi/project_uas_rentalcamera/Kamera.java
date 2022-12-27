package com.roynaldi.project_uas_rentalcamera;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Kamera implements Serializable {

    @Exclude
    private String key;
    private String kode;
    private String merk;
    private String harga;

    public Kamera(){}
    public Kamera(String kode, String merk, String harga) {
        this.kode = kode;
        this.merk = merk;
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
