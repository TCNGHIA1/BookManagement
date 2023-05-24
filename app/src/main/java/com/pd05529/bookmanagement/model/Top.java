package com.pd05529.bookmanagement.model;

public class Top {
    private String tenSach;
    private int soLuong;

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Top() {
    }

    public Top(String tenSach, int soluong) {
        this.tenSach = tenSach;
        this.soLuong = soluong;
    }
}
