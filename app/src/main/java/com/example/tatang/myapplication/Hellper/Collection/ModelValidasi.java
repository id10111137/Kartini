package com.example.tatang.myapplication.Hellper.Collection;

public class ModelValidasi {


    private String id_user;
    private String nama;
    private String photo;
    private String jumlah_ik;
    private String bobot;
    private String capai;
    private String total_kpi;
    private String pencapaian;
    private String IP;
    private String IPK;
    private String nama_subid;
    private String status;
    private String verifikasi;
    private String deadline;
    private String lock;

    public ModelValidasi(String id_user, String nama, String photo, String jumlah_ik, String bobot, String capai, String total_kpi, String pencapaian, String IP, String IPK, String nama_subid, String status, String verifikasi, String deadline, String lock){
        this.id_user = id_user;
        this.nama = nama;
        this.photo = photo;
        this.jumlah_ik = jumlah_ik;
        this.bobot = bobot;
        this.capai = capai;
        this.total_kpi = total_kpi;
        this.pencapaian = pencapaian;
        this.IP = IP;
        this.IPK = IPK;
        this.nama_subid = nama_subid;
        this.status = status;
        this.verifikasi = verifikasi;
        this.deadline = deadline;
        this.lock = lock;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJumlah_ik() {
        return jumlah_ik;
    }

    public void setJumlah_ik(String jumlah_ik) {
        this.jumlah_ik = jumlah_ik;
    }

    public String getBobot() {
        return bobot;
    }

    public void setBobot(String bobot) {
        this.bobot = bobot;
    }

    public String getCapai() {
        return capai;
    }

    public void setCapai(String capai) {
        this.capai = capai;
    }

    public String getTotal_kpi() {
        return total_kpi;
    }

    public void setTotal_kpi(String total_kpi) {
        this.total_kpi = total_kpi;
    }

    public String getPencapaian() {
        return pencapaian;
    }

    public void setPencapaian(String pencapaian) {
        this.pencapaian = pencapaian;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getIPK() {
        return IPK;
    }

    public void setIPK(String IPK) {
        this.IPK = IPK;
    }

    public String getNama_subid() {
        return nama_subid;
    }

    public void setNama_subid(String nama_subid) {
        this.nama_subid = nama_subid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }



}
