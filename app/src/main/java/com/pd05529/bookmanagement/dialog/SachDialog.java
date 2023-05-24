package com.pd05529.bookmanagement.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.DAO.LoaiSachDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.model.Sach;
import com.pd05529.bookmanagement.ui.SachFragment;

import java.util.Date;

public class SachDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextInputLayout layoutId,layoutName,layoutTacGia;
    public Spinner spn;
    private Button btnSave, btnClose;
    private boolean mode;

    public SachDialog(Context context, SachFragment fragment, Sach... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_sach,null);
        layoutId = view.findViewById(R.id.layoutId);
        layoutName = view.findViewById(R.id.layoutName);
        layoutTacGia = view.findViewById(R.id.layoutTacGia);
        btnSave = view.findViewById(R.id.btnSave);
        btnClose = view.findViewById(R.id.btnClose);
        spn = view.findViewById(R.id.spn);

        ArrayAdapter<LoaiSach> adapter = new ArrayAdapter<LoaiSach>(context,
                android.R.layout.simple_list_item_1,fragment.loaiSachDAO.getAll());
        spn.setAdapter(adapter);
        //Kiem tra du lieu
        if(args!=null && args.length>0){
            layoutId.getEditText().setText(""+args[0].getMaSach());
            layoutName.getEditText().setText(args[0].getTenSach());
            layoutTacGia.getEditText().setText(args[0].getTacGia());
            mode = true;
        } else{
            mode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
        //Su kien nut nhan
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(v -> {
            String id = layoutId.getEditText().getText().toString().trim();
            String name = layoutName.getEditText().getText().toString().trim();
            String tacgia = layoutTacGia.getEditText().getText().toString().trim();


            if (!validate(layoutTacGia)||!validate(layoutName)) {
                return;
            } else {
                Sach ob = new Sach();
                ob.setTenSach(name);
                ob.setTacGia(tacgia);
                ob.setMaLoai(adapter.getItem(spn.getSelectedItemPosition()).getMaLoai());
                if (mode) {
                    ob.setMaSach(Integer.parseInt(id));
                    fragment.dao.update(ob);
                    Toast.makeText(context, "Dữ liệu đã sửa đổi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    fragment.dao.insert(ob);
                    Toast.makeText(context, "Dữ liệu Đã được thêm", Toast.LENGTH_SHORT).show();
                    clear();
                }
                fragment.updateAdapter();
            }
        });

    }
    public boolean validate(TextInputLayout input){
        String str = input.getEditText().getText().toString().trim();
        if(str.isEmpty()){
            input.setError("Không để trống dữ liệu");
            return false;
        }else{
            input.setError("");
            return true;
        }
    }
    public void show(){
        dialog.show();
    }
    //clear input
    public void clear(){
        layoutName.setError("");
        layoutName.getEditText().setText("");
        layoutTacGia.setError("");
        layoutTacGia.getEditText().setText("");
    }
}
