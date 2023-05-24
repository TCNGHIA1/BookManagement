package com.pd05529.bookmanagement.model;

import java.util.Date;

public class ThanhVien {
    private int maTV;
    private int phone;
    private String hoTen;
    private Date namSinh;

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Date namSinh) {
        this.namSinh = namSinh;
    }

    public ThanhVien() {
    }

    public ThanhVien(int maTV, int phone, String hoTen, Date namSinh) {
        this.maTV = maTV;
        this.phone = phone;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return maTV + " - " + hoTen;
    }
}
