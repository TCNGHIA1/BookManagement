package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.LoaiSach;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    private SQLiteDatabase db;
    private Context context;
    public LoaiSachDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Them
    public long insert(LoaiSach ob){
        ContentValues mValues = new ContentValues();
        mValues.put("tenLoai",ob.getTenLoai());
        return db.insert("LoaiSach",null,mValues);
    }
    //Sua
    public int update(LoaiSach ob){
        ContentValues mValues = new ContentValues();
        mValues.put("tenLoai",ob.getTenLoai());
        return db.update("LoaiSach",mValues,"maLoai=?",new String[]{String.valueOf(ob.getMaLoai())});
    }
    //Xoa
    public int delete(String id) {
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }
    //Get tat ca data
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    //get data theo id
    public LoaiSach getId(String id){
        String sql = "select * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }
    //get Data nhieu tham so
    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String... selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach ob = new LoaiSach();
            ob.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            ob.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(ob);
        }
        return list;
    }
}
