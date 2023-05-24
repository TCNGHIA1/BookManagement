package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.model.Sach;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private SQLiteDatabase db;
    private Context context;
    public SachDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Them
    public long insert(Sach ob){
        ContentValues mValues = new ContentValues();
        mValues.put("tacGia",ob.getTacGia());
        mValues.put("tenSach",ob.getTenSach());
        mValues.put("maLoai",ob.getMaLoai());
        return db.insert("Sach",null,mValues);
    }
    public int update(Sach ob){
        ContentValues mValues = new ContentValues();
        mValues.put("tacGia",ob.getTacGia());
        mValues.put("tenSach",ob.getTenSach());
        mValues.put("maLoai",ob.getMaLoai());
        return db.update("Sach",mValues,"maSach=?",new String[]{String.valueOf(ob.getMaSach())});
    }
    //Xoa
    public int delete(String id) {
        return db.delete("Sach", "maSach=?", new String[]{id});
    }
    //Get tat ca data
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    //get data theo id
    public Sach getId(String id){
        String sql = "select * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
    //get Data nhieu tham so
    @SuppressLint("Range")
    public List<Sach> getData(String sql, String... selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Sach ob = new Sach();
            ob.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            ob.setTenSach(cursor.getString(cursor.getColumnIndex("tenSach")));
            ob.setTacGia(cursor.getString(cursor.getColumnIndex("tacGia")));
            ob.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            list.add(ob);
        }
        return list;
    }
    //Kiểm tra loại sách
    public List<Sach> getArrgsLoai(String str){
        String sql = "SELECT * FROM Sach WHERE maLoai=?";
        return getData(sql,str);
    }

    //Xoa theo loai sach
    public int deleteLoai(String loai){
        return db.delete("Sach","maLoai=?",new String[]{loai});
    }
}
