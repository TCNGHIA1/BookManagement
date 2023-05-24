package com.pd05529.bookmanagement.model;

public class ThuThu {
    private String maTT;
    private String hoTen;
    private String matKhau;
    private int phone;
    private int role;

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public ThuThu(String maTT, String hoTen, String matKhau, int phone, int role) {
        this.maTT = maTT;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.phone = phone;
        this.role = role;
    }

    public ThuThu() {
    }

    @Override
    public String toString() {
        return maTT + " - "+ hoTen;
    }
}
