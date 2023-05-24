package com.pd05529.bookmanagement.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.ThuThu;
import com.pd05529.bookmanagement.ui.ThuThuFragment;

public class ThuThuDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextInputLayout layoutId,layoutName,layoutPhone,layoutPass;
    public Spinner spn;
    private Button btnSave, btnClose;
    private boolean mode;

    public ThuThuDialog(Context context, ThuThuFragment fragment, ThuThu... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_thuthu,null);
        layoutId = view.findViewById(R.id.layoutId);
        layoutName = view.findViewById(R.id.layoutName);
        layoutPhone = view.findViewById(R.id.layoutPhone);
        layoutPass = view.findViewById(R.id.layoutPass);


        btnSave = view.findViewById(R.id.btnSave);
        btnClose = view.findViewById(R.id.btnClose);
        spn = view.findViewById(R.id.spn);
        //Spiner role
        String[] list = {"Admin","Thủ thư"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,list);
        spn.setAdapter(adapter);
        //Kiem tra du lieu
        if(args!=null && args.length>0){
            layoutId.getEditText().setText(args[0].getMaTT());
            layoutName.getEditText().setText(args[0].getHoTen());
            layoutPhone.getEditText().setText(""+args[0].getPhone());
            layoutPass.getEditText().setText(args[0].getMatKhau());
            layoutId.getEditText().setEnabled(false);
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
            String phone = layoutPhone.getEditText().getText().toString().trim();
            String pass = layoutPass.getEditText().getText().toString().trim();

            if (!validate(layoutName)||!validate(layoutPass)||!validate(layoutPhone)) {
                return;
            } else {
                ThuThu ob = new ThuThu();
                ob.setMaTT(id);
                ob.setHoTen(name);
                ob.setPhone(Integer.parseInt(phone));
                ob.setMatKhau(pass);
                ob.setRole(spn.getSelectedItemPosition());
                if (mode) {
                    fragment.dao.update(ob);
                    Toast.makeText(context, "Dữ liệu đã sửa đổi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    layoutId.setError("");
                    if(fragment.dao.getId(id)!=null){
                        layoutId.setError("Tài khoản đã tồn tại");
                    }else{
                        fragment.dao.insert(ob);
                        Toast.makeText(context, "Dữ liệu Đã được thêm", Toast.LENGTH_SHORT).show();
                        clear();
                    }
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
    public void clear(){
        layoutName.setError("");
        layoutName.getEditText().setText("");
        layoutPhone.setError("");
        layoutPhone.getEditText().setText("");
        layoutPass.getEditText().setText("");
        layoutPass.setError("");
    }
}
