package com.pd05529.bookmanagement.dialog;

import static com.pd05529.bookmanagement.R.drawable.ic_draw_pass;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.DAO.LoaiSachDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.LoaiSachRecyclerViewAdapter;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.ui.LoaiSachFragment;

import java.util.List;

public class LoaiSachDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextInputLayout layoutId,layoutName;
    private Button btnSave, btnClose;
    private boolean mode;
    public LoaiSachDialog(Context context, LoaiSachFragment loaiSachFragment, LoaiSach... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_loaisach,null);
        layoutId = view.findViewById(R.id.layoutId);
        layoutName = view.findViewById(R.id.layoutName);
        btnSave = view.findViewById(R.id.btnSave);
        btnClose = view.findViewById(R.id.btnClose);
        //Kiem tra du lieu
        if(args!=null && args.length>0){
            layoutId.getEditText().setText(""+args[0].getMaLoai());
            layoutName.getEditText().setText(args[0].getTenLoai());
            mode = true;
        } else{
            mode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        //Su kien nut nhan
        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(v -> {
            String id = layoutId.getEditText().getText().toString().trim();
            String name = layoutName.getEditText().getText().toString().trim();
            if (!validate(layoutName)) {
                return;
            } else {
                LoaiSach ob = new LoaiSach();
                ob.setTenLoai(name);
                if (mode) {
                    ob.setMaLoai(Integer.parseInt(id));
                    loaiSachFragment.dao.update(ob);
                    Toast.makeText(context, "Dữ liệu đã sửa đổi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    loaiSachFragment.dao.insert(ob);
                    Toast.makeText(context, "Dữ liệu Đã được thêm", Toast.LENGTH_SHORT).show();
                    clear();
                }
                loaiSachFragment.updateAdapter();
            }
        });
        dialog = builder.create();
    }
    public void show(){
        dialog.show();
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
    public void clear(){
        layoutName.setError("");
        layoutName.getEditText().setText("");

    }
}
