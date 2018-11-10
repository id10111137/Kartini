package com.example.tatang.myapplication.Hellper.Collection;

/**
 * Created by Administrator on 8/13/2018.
 */

public class ModelEvidenMB {

    String id_apb;
    String id_indikator;
    String id_unit;
    String id_perspektif;
    String id_bidang;
    String bobot;
    String status;
    String indikator;
    String MLI;
    String info;
    String bulan;
    String tahun;
    String filter_waktu;
    String status_response;

    public ModelEvidenMB(String id_apb, String id_indikator, String id_unit, String id_perspektif, String id_bidang,String bobot, String status, String indikator, String MLI, String info, String bulan, String tahun, String filter_waktu,String status_response){
        this.id_apb = id_apb;
        this.id_indikator =id_indikator;
        this.id_unit = id_unit;
        this.id_perspektif = id_perspektif;
        this.id_bidang = id_bidang;
        this.bobot= bobot;
        this.status =status;
        this.indikator = indikator;
        this.MLI = MLI;
        this.info = info;
        this.bulan = bulan;
        this.tahun = tahun;
        this.filter_waktu = filter_waktu;
        this.status_response = status_response;
    }

    public String getId_apb() {
        return id_apb;
    }

    public void setId_apb(String id_apb) {
        this.id_apb = id_apb;
    }

    public String getId_indikator() {
        return id_indikator;
    }

    public void setId_indikator(String id_indikator) {
        this.id_indikator = id_indikator;
    }

    public String getId_unit() {
        return id_unit;
    }

    public void setId_unit(String id_unit) {
        this.id_unit = id_unit;
    }

    public String getId_perspektif() {
        return id_perspektif;
    }

    public void setId_perspektif(String id_perspektif) {
        this.id_perspektif = id_perspektif;
    }

    public String getId_bidang() {
        return id_bidang;
    }

    public void setId_bidang(String id_bidang) {
        this.id_bidang = id_bidang;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIndikator() {
        return indikator;
    }

    public void setIndikator(String indikator) {
        this.indikator = indikator;
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

    public String getStatus_response() {
        return status_response;
    }

    public void setStatus_response(String status_response) {
        this.status_response = status_response;
    }






}
