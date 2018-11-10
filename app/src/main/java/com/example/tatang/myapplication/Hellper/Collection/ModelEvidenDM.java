package com.example.tatang.myapplication.Hellper.Collection;

public class ModelEvidenDM {






    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_indikator() {
        return id_indikator;
    }

    public void setId_indikator(String id_indikator) {
        this.id_indikator = id_indikator;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
    }

    public String getId_prosedur() {
        return id_prosedur;
    }

    public void setId_prosedur(String id_prosedur) {
        this.id_prosedur = id_prosedur;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getNama_prosedur() {
        return nama_prosedur;
    }

    public void setNama_prosedur(String nama_prosedur) {
        this.nama_prosedur = nama_prosedur;
    }

    public String getBerlaku() {
        return berlaku;
    }

    public void setBerlaku(String berlaku) {
        this.berlaku = berlaku;
    }

    public String getRevisi() {
        return revisi;
    }

    public void setRevisi(String revisi) {
        this.revisi = revisi;
    }

    public String getId_eviden() {
        return id_eviden;
    }

    public void setId_eviden(String id_eviden) {
        this.id_eviden = id_eviden;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMLI() {
        return MLI;
    }

    public void setMLI(String MLI) {
        this.MLI = MLI;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getFilter_waktu() {
        return filter_waktu;
    }

    public void setFilter_waktu(String filter_waktu) {
        this.filter_waktu = filter_waktu;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }


    String id_user;
    String id_indikator;
    String indikator;
    String id_prosedur;
    String nomor;
    String nama_prosedur;
    String berlaku;
    String revisi;
    String id_eviden;
    String dokumen;
    String bobot;
    String status;
    String MLI;
    String info;
    String bulan;
    String tahun;
    String filter_waktu;

    public String getStatus_response() {
        return status_response;
    }

    public void setStatus_response(String status_response) {
        this.status_response = status_response;
    }

    String status_response;

    public ModelEvidenDM(String id_user,
            String id_indikator,
            String indikator,
            String id_prosedur,
            String nomor,
            String nama_prosedur,
            String berlaku,
            String revisi,
            String id_eviden,
            String dokumen,
            String bobot,
            String status,
            String MLI,
            String info,
            String bulan,
            String tahun,
            String filter_waktu,
            String status_response){
        this.id_user = id_user;
        this.id_indikator = id_indikator;
        this.indikator = indikator;
        this.id_prosedur = id_prosedur;
        this.nomor = nomor;
        this.nama_prosedur = nama_prosedur;
        this.berlaku = berlaku;
        this.revisi = revisi;
        this.id_eviden = id_eviden;
        this.dokumen = dokumen;
        this.bobot = bobot;
        this.status = status;
        this.MLI = MLI;
        this.info = info;
        this.bulan = bulan;
        this.tahun = tahun;
        this.filter_waktu =filter_waktu ;
        this.status_response = status_response;
    }


}
