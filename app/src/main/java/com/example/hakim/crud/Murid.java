package com.example.hakim.crud;

public class Murid {
    private Integer id;
    private String nama;
    private String alamat;
    private String tgl_lahir;
    private String jenis_Kelamin;
    private String agama;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJenis_Kelamin() {
        return jenis_Kelamin;
    }

    public void setJenis_Kelamin(String jenis_Kelamin) {
        this.jenis_Kelamin = jenis_Kelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public Murid(){

    }

    public Murid(String nama, String alamat, String tgl_lahir, String jenis_Kelamin, String agama) {
        this.nama = nama;
        this.alamat = alamat;
        this.tgl_lahir = tgl_lahir;
        this.tgl_lahir = tgl_lahir;
        this.jenis_Kelamin = jenis_Kelamin;
        this.agama = agama;

    }
}
