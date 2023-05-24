package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.ThanhVien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThanhVienDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ThanhVienDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Them
    public long insert(ThanhVien ob){
        ContentValues mValues = new ContentValues();
        mValues.put("hoTen",ob.getHoTen());
        mValues.put("phone",ob.getPhone());
        mValues.put("namSinh",sdf.format(ob.getNamSinh()));
        return db.insert("ThanhVien",null,mValues);
    }
    public int update(ThanhVien ob){
        ContentValues mValues = new ContentValues();
        mValues.put("hoTen",ob.getHoTen());
        mValues.put("phone",ob.getPhone());
        mValues.put("namSinh",sdf.format(ob.getNamSinh()));
        return db.update("ThanhVien",mValues,"maTV=?",new String[]{String.valueOf(ob.getMaTV())});
    }
    //Xoa
    public int delete(String id) {
        return db.delete("ThanhVien", "maTV=?", new String[]{id});
    }
    //Get tat ca data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }
    //get data theo id
    public ThanhVien getId(String id){
        String sql = "select * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
    //get Data nhieu tham so
    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String... selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien ob = new ThanhVien();
            ob.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            ob.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            try {
                ob.setNamSinh(sdf.parse(cursor.getString(cursor.getColumnIndex("namSinh"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            ob.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("phone"))));
            list.add(ob);
        }
        return list;
    }
}














