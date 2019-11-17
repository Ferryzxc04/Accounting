package com.example.uts;

public class list_history {
    private int nominal;
    private String jenis;
    private String uraian;
    private String tanggal;
    private String waktu;

    public list_history() {

    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public String getUraian() {
        return uraian;
    }

    public void setUraian(String uraian) {
        this.uraian = uraian;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public list_history(int nominal, String jenis, String uraian, String tanggal, String waktu) {
        this.nominal = nominal;
        this.jenis = jenis;
        this.uraian = uraian;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }
}
