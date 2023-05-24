package com.pd05529.bookmanagement.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String NAME = "Book_Management";
    private static final int VER = 1;

    public DbHelper(@Nullable Context context) {
        super(context, NAME, null, VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng
        db.execSQL(Data_SQLite.createThuThu);
        db.execSQL(Data_SQLite.createThanhVien);
        db.execSQL(Data_SQLite.createLoaiSach);
        db.execSQL(Data_SQLite.createSach);
        db.execSQL(Data_SQLite.createPhieuMuon);
        //Thêm dữ liệu sẵn
        db.execSQL(Data_SQLite.INSERT_THUTHU);
        db.execSQL(Data_SQLite.INSERT_LOAISACH);
        db.execSQL(Data_SQLite.INSERT_THANHVIEN);
        db.execSQL(Data_SQLite.INSERT_SACH);
        db.execSQL(Data_SQLite.INSERT_PHIEUMUON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Xoa bang
        db.execSQL("drop table if exists PhieuMuon");
        db.execSQL("drop table if exists Sach");
        db.execSQL("drop table if exists LoaiSach");
        db.execSQL("drop table if exists ThanhVien");
        db.execSQL("drop table if exists ThuThu");
        onCreate(db);
    }
}
