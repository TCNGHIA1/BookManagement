package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {
    private SQLiteDatabase db;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Them
    public long insert(PhieuMuon ob){
        ContentValues mValues = new ContentValues();
        mValues.put("maTT",ob.getMaTT());
        mValues.put("maTV",ob.getMaTV());
        mValues.put("maSach",ob.getMaSach());
        mValues.put("tienThue",ob.getTienThue());
        mValues.put("ngay",sdf.format(ob.getNgay()));
        mValues.put("traSach",ob.getTraSach());
        return db.insert("PhieuMuon",null,mValues);
    }
    public int update(PhieuMuon ob){
        ContentValues mValues = new ContentValues();
        mValues.put("maTT",ob.getMaTT());
        mValues.put("maTV",ob.getMaTV());
        mValues.put("maSach",ob.getMaSach());
        mValues.put("tienThue",ob.getTienThue());
        mValues.put("ngay",sdf.format(ob.getNgay()));
        mValues.put("traSach",ob.getTraSach());
        return db.update("PhieuMuon",mValues,"maPM=?",new String[]{String.valueOf(ob.getMaPM())});
    }
    //Xoa
    public int delete(String id) {
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }
    //Get tat ca data
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    //get data theo id
    public PhieuMuon getId(String id){
        String sql = "select * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    //get Data nhieu tham so
    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String... selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            PhieuMuon ob = new PhieuMuon();
            ob.setMaPM(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maPM")))); ;
            ob.setMaTV(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maTV"))));
            ob.setMaSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSach"))));
            ob.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            try {
                ob.setNgay(sdf.parse(cursor.getString(cursor.getColumnIndex("ngay")))); ;
            } catch (Exception ex){
                return null;
            }
            ob.setTienThue(Integer.parseInt(cursor.getString(cursor.getColumnIndex("tienThue"))));
            ob.setTraSach(Integer.parseInt(cursor.getString(cursor.getColumnIndex("traSach"))));
            list.add(ob);
        }
        return list;
    }
    //Kiểm tra sách
    public List<PhieuMuon> getArrgsSach(String str){
        String sql = "SELECT * FROM PhieuMuon WHERE maSach=?";
        return getData(sql,str);
    }
    //Xoa theo loai sach
    public int deleteSach(String str){
        return db.delete("PhieuMuon","maSach=?",new String[]{str});
    }







}
