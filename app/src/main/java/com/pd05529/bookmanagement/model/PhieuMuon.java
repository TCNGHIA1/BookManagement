package com.pd05529.bookmanagement.model;

import java.util.Date;

public class PhieuMuon {
    private int maPM;
    private int maSach;
    private String maTT;
    private int maTV;
    private int tienThue;
    private Date ngay;
    private int traSach;

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, int maSach, String maTT, int maTV, int tienThue, Date ngay, int traSach) {
        this.maPM = maPM;
        this.maSach = maSach;
        this.maTT = maTT;
        this.maTV = maTV;
        this.tienThue = tienThue;
        this.ngay = ngay;
        this.traSach = traSach;
    }
}
