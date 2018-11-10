package com.example.tatang.myapplication.Hellper.Collection;

/**
 * Created by tatang.it on 11/24/2017.
 */

public class UserModel {




    private String id_user;
    private String nama_unit;
    private String username;
    private String password;
    private String nama_user;
    private String email;
    private String nama_bidang;
    private String id_subid;
    private String nama_subid;
    private String status_akun;
    private String status_user;
    private String last_login;
    private String login;
    private String deadline;
    private String lock_upload;
    private String photo;
    private String tujuan_jabatan;
    private String cookie;
    private String id_bidang;


    public UserModel(String id_user, String nama_unit, String username, String password, String nama_user, String email, String nama_bidang, String id_bidang, String id_subid , String nama_subid, String status_akun, String status_user, String last_login, String login, String deadline, String lock_upload, String photo, String tujuan_jabatan, String cookie) {
        this.id_user= id_user;
        this.nama_unit = nama_unit;
        this.username= username;
        this.password= password;
        this.nama_user= nama_user;
        this.email= email;
        this.nama_bidang= nama_bidang;
        this.id_bidang= id_bidang;
        this.id_subid= id_subid;
        this.nama_subid= nama_subid;
        this.status_akun= status_akun;
        this.status_user= status_user;
        this.last_login= last_login;
        this.login= login;
        this.deadline= deadline;
        this.lock_upload= lock_upload;
        this.photo= photo;
        this.tujuan_jabatan= tujuan_jabatan;
        this.cookie= cookie;
    }



    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_unit() {
        return nama_unit;
    }

    public void setNama_unit(String nama_unit) {
        this.nama_unit = nama_unit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama_bidang() {
        return nama_bidang;
    }

    public void setNama_bidang(String nama_bidang) {
        this.nama_bidang = nama_bidang;
    }

    public String getId_subid() {
        return id_subid;
    }

    public void setId_subid(String id_subid) {
        this.id_subid = id_subid;
    }

    public String getNama_subid() {
        return nama_subid;
    }

    public void setNama_subid(String nama_subid) {
        this.nama_subid = nama_subid;
    }

    public String getStatus_akun() {
        return status_akun;
    }

    public void setStatus_akun(String status_akun) {
        this.status_akun = status_akun;
    }

    public String getStatus_user() {
        return status_user;
    }

    public void setStatus_user(String status_user) {
        this.status_user = status_user;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getLock_upload() {
        return lock_upload;
    }

    public void setLock_upload(String lock_upload) {
        this.lock_upload = lock_upload;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTujuan_jabatan() {
        return tujuan_jabatan;
    }

    public void setTujuan_jabatan(String tujuan_jabatan) {
        this.tujuan_jabatan = tujuan_jabatan;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getId_bidang() {
        return id_bidang;
    }

    public void setId_bidang(String id_bidang) {
        this.id_bidang = id_bidang;
    }


}
