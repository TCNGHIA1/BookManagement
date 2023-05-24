package com.pd05529.bookmanagement.model;

public class Sach {
    private int maSach;
    private int maLoai;
    private String tenSach;
    private String tacGia;

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Sach(int maSach, int maLoai, String tenSach, String tacGia) {
        this.maSach = maSach;
        this.maLoai = maLoai;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
    }

    public Sach() {
    }

    @Override
    public String toString() {
        return maSach + " - " + tenSach;
    }
}
