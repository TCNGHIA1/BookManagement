package com.pd05529.bookmanagement.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.ThanhVien;
import com.pd05529.bookmanagement.ui.ThanhVienFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThanhVienDialog{
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextInputLayout layoutId,layoutName,layoutPhone;

    public EditText edNamSinh;
    private Button btnSave, btnClose,btnNamSinh;
    private boolean mode;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ThanhVienDialog(Context context, ThanhVienFragment fragment, ThanhVien... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_thanhvien,null);
        layoutId = view.findViewById(R.id.layoutId);
        layoutName = view.findViewById(R.id.layoutName);
        layoutPhone = view.findViewById(R.id.layoutPhone);

        edNamSinh = view.findViewById(R.id.edNamSinh);
        btnSave = view.findViewById(R.id.btnSave);
        btnClose = view.findViewById(R.id.btnClose);
        btnNamSinh = view.findViewById(R.id.btnNamSinh);
        //Kiem tra du lieu
        edNamSinh.setEnabled(false);
        if(args!=null && args.length>0){
            layoutId.getEditText().setText(String.valueOf(args[0].getMaTV()));
            layoutName.getEditText().setText(args[0].getHoTen());
            edNamSinh.setText(sdf.format(args[0].getNamSinh()));
            layoutPhone.getEditText().setText(String.valueOf(args[0].getPhone()));
            mode = true;
        } else{
            mode = false;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
        //Su kien nut nhan
        btnNamSinh.setOnClickListener(v -> {
            Calendar mCalendar = Calendar.getInstance();
            int mYear = mCalendar.get(Calendar.YEAR);
            int mMoth = mCalendar.get(Calendar.MONTH);
            int day = mCalendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            edNamSinh.setText(year+"/"+(month+1)+"/"+dayOfMonth);
                        }
                    },mYear,mMoth,day);
            datePickerDialog.show();
        });

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btnSave.setOnClickListener(v -> {
            String id = layoutId.getEditText().getText().toString().trim();
            String name = layoutName.getEditText().getText().toString().trim();
            String phone = layoutPhone.getEditText().getText().toString().trim();

            if (!validate(layoutName)||!validate(layoutPhone)||!validate(edNamSinh)) {
                return;
            } else {
                ThanhVien ob = new ThanhVien();
                ob.setHoTen(name);
                ob.setPhone(Integer.parseInt(phone));
                try {
                    ob.setNamSinh(sdf.parse(edNamSinh.getText().toString()));
                } catch (Exception e) {
                    edNamSinh.setError("Sai định dạng");
                }
                if (mode) {
                    ob.setMaTV(Integer.parseInt(id));
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
    public boolean validate(EditText edt){
        String str = edt.getText().toString().trim();
        if(str.isEmpty()){
            edt.setError("Không để trống dữ liệu");
            return false;
        }else {
            edt.setError(null);
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
        edNamSinh.setText("");
        edNamSinh.setError("");
    }
}
