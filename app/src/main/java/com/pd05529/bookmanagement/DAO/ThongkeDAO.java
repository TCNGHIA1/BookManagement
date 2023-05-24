package com.pd05529.bookmanagement.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.pd05529.bookmanagement.DBHelper.DbHelper;
import com.pd05529.bookmanagement.model.Sach;
import com.pd05529.bookmanagement.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongkeDAO {
    SQLiteDatabase db ;
    Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ThongkeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //THong ke top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sql = "select maSach,count(maSach) as soluong FROM PhieuMuon GROUP BY maSach ORDER BY soluong " +
                "DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            Top top = new Top();
            Sach sach = sachDAO.getId(cursor.getString(cursor.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(cursor.getString(cursor.getColumnIndex("soluong"))));
            list.add(top);
        }
        return list;
    }
    //Thong ke doanh thu
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = "select Sum(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{tuNgay,denNgay});
        while (cursor.moveToNext()){
                try{
                    list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));
                } catch (NumberFormatException e){
                    return 0;
                }
        }
        return list.get(0);
    }
}
