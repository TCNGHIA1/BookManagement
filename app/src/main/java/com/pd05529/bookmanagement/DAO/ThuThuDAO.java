package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class ThuThuDAO {
    private SQLiteDatabase db;
    public ThuThuDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Them
    public long insert(ThuThu ob){
        ContentValues mValues = new ContentValues();
        mValues.put("maTT",ob.getMaTT());
        mValues.put("hoTen",ob.getHoTen());
        mValues.put("matKhau",ob.getMatKhau());
        mValues.put("phone",ob.getPhone());
        mValues.put("role",ob.getRole());
        return db.insert("ThuThu",null,mValues);
    }
    public int update(ThuThu ob){
        ContentValues mValues = new ContentValues();
        mValues.put("hoTen",ob.getHoTen());
        mValues.put("matKhau",ob.getMatKhau());
        mValues.put("phone",ob.getPhone());
        mValues.put("role",ob.getRole());
        return db.update("ThuThu",mValues,"maTT=?",new String[]{String.valueOf(ob.getMaTT())});
    }
    //Xoa
    public int delete(String id) {

        return db.delete("ThuThu", "maTT=?", new String[]{id});
    }
    //Get tat ca data
    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }
    //get data theo id
    public ThuThu getId(String id){
        String sql = "select * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        if(list.size()==0){
            return null;
        }
        return list.get(0);
    }
    //get Data nhieu tham so
    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String... selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu ob = new ThuThu();
            ob.setMaTT(cursor.getString(cursor.getColumnIndex("maTT")));
            ob.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            ob.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("phone"))));
            ob.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            ob.setRole(Integer.parseInt(cursor.getString(cursor.getColumnIndex("role"))));
            list.add(ob);
        }
        return list;
    }
    //Check login
    public int checkLogin(String id,String pass){
        String sql = "Select * from ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,pass);
        if(list.size()==0){
            return -1;
        }
        return 1;
    }
}
