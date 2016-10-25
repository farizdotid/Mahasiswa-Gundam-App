package com.farizdotid.mahasiswagundam.model;

/**
 * Created by farizdotid on 25-Oct-16.
 */

public class Mahasiswa {

    private int id;
    private String nama;
    private String tempat_lahir;

    public Mahasiswa(String nama, String tempat_lahir) {
        this.nama = nama;
        this.tempat_lahir = tempat_lahir;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }
}
