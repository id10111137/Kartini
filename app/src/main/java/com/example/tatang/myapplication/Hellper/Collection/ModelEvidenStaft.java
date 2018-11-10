package com.example.tatang.myapplication.Hellper.Collection;

/**
 * Created by Administrator on 7/9/2018.
 */

public class ModelEvidenStaft {



    public String getId_ik() {
        return id_ik;
    }

    public void setId_ik(String id_ik) {
        this.id_ik = id_ik;
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

    public String getNama_ik() {
        return nama_ik;
    }

    public void setNama_ik(String nama_ik) {
        this.nama_ik = nama_ik;
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

    public String getDokumen_ik() {
        return dokumen_ik;
    }

    public void setDokumen_ik(String dokumen_ik) {
        this.dokumen_ik = dokumen_ik;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_prosedur() {
        return nama_prosedur;
    }

    public void setNama_prosedur(String nama_prosedur) {
        this.nama_prosedur = nama_prosedur;
    }

    public String getNomor_pro() {
        return nomor_pro;
    }

    public void setNomor_pro(String nomor_pro) {
        this.nomor_pro = nomor_pro;
    }

    public String getDokumen() {
        return dokumen;
    }

    public void setDokumen(String dokumen) {
        this.dokumen = dokumen;
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

    String id_ik;
    String id_prosedur;
    String nomor;
    String nama_ik;
    String bobot;
    String status;
    String dokumen_ik;
    String id_user;
    String nama_prosedur;
    String nomor_pro;
    String dokumen;
    String MLI;
    String info;
    String tahun;
    String filter_waktu;

    public ModelEvidenStaft(String id_ik, String id_prosedur, String nomor, String nama_ik, String bobot,String status, String dokumen_ik,String id_user, String nama_prosedur, String nomor_pro, String dokumen, String MLI, String info,String tahun, String filter_waktu){
        this.id_ik = id_ik;
        this.id_prosedur = id_prosedur;
        this.nomor= nomor;
        this.nama_ik= nama_ik;
        this.bobot= bobot;
        this.status= status;
        this.dokumen_ik= dokumen_ik;
        this.id_user= id_user;
        this.nama_prosedur= nama_prosedur;
        this.nomor_pro= nomor_pro;
        this.dokumen= dokumen;
        this.MLI= MLI;
        this.info= info;
        this.tahun= tahun;
        this.filter_waktu= filter_waktu;
    }




}
