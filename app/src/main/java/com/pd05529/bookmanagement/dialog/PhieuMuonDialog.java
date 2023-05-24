package com.pd05529.bookmanagement.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.Sach;
import com.pd05529.bookmanagement.model.ThanhVien;
import com.pd05529.bookmanagement.model.ThuThu;
import com.pd05529.bookmanagement.ui.PhieuMuonFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PhieuMuonDialog {
    public AlertDialog dialog;

    Spinner spnTT,spnSach,spnTV,spnTraSach;
    TextInputLayout layoutId,layoutTien,layoutDate;
    private Button btnSave,btnClose,btnNgay;
    public boolean mode;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    public PhieuMuonDialog(Context context, PhieuMuonFragment fragment, PhieuMuon... args){
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_phieumuon,null);
        spnTT = view.findViewById(R.id.spnTT);
        spnSach = view.findViewById(R.id.spnSach);
        spnTV = view.findViewById(R.id.spnTV);
        spnTraSach = view.findViewById(R.id.spnTraSach);

        layoutId = view.findViewById(R.id.layoutId);
        layoutTien = view.findViewById(R.id.layoutTien);
        layoutDate = view.findViewById(R.id.layoutDate);

        btnSave = view.findViewById(R.id.btnSave);
        btnClose = view.findViewById(R.id.btnClose);
        btnNgay = view.findViewById(R.id.btnNgay);

        ArrayAdapter<ThuThu> thuThuSpinnerAdapter = new ArrayAdapter<ThuThu>(context,
                android.R.layout.simple_list_item_1,fragment.thuThuDAO.getAll());
        spnTT.setAdapter(thuThuSpinnerAdapter);

        ArrayAdapter<ThanhVien> thanhVienSpinnerAdapter = new ArrayAdapter<ThanhVien>(context,
                android.R.layout.simple_list_item_1,fragment.thanhVienDAO.getAll());
        spnTV.setAdapter(thanhVienSpinnerAdapter);


        ArrayAdapter<Sach> sachSpinnerAdapter = new ArrayAdapter<Sach>(context,
                android.R.layout.simple_list_item_1,fragment.sachDAO.getAll());
        spnSach.setAdapter(sachSpinnerAdapter);

        String[] listTraSach = {"Đã trả sách","Chưa trả sách"};
        spnTraSach.setAdapter(
                new ArrayAdapter<String>(context.getApplicationContext(), android.R.layout.simple_list_item_1,
                        listTraSach)
        );
        //Ngay thue default
        layoutDate.getEditText().setText(sdf.format(new Date()));
        layoutDate.setEnabled(false);

        //Điền thông tin vào khi update
        if(args!=null &args.length>0){
            layoutId.getEditText().setText(String.valueOf(args[0].getMaPM()));
            layoutTien.getEditText().setText(String.valueOf(args[0].getTienThue()));
            layoutDate.getEditText().setText(sdf.format(args[0].getNgay()));
            mode = true;
        }else{
            mode = false;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view);
        dialog = builder.create();
        btnNgay.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            layoutDate.getEditText().setText(year+"/"+(month+1)+"/"+dayOfMonth);
                        }
                    },mYear,mMonth,mDay);
            datePickerDialog.show();
        });
        btnClose.setOnClickListener(v ->{
            dialog.dismiss();
        });
        btnSave.setOnClickListener(v ->{
            String id = layoutId.getEditText().getText().toString().trim();
            String tien = layoutTien.getEditText().getText().toString().trim();
            String ngay = layoutDate.getEditText().getText().toString().trim();
            if(!validate(tien,layoutTien)){
                return;
            }else{
                PhieuMuon obj = new PhieuMuon();
                obj.setMaTT((thuThuSpinnerAdapter.getItem(spnTT.getSelectedItemPosition())).getMaTT());
                obj.setMaTV((thanhVienSpinnerAdapter.getItem(spnTV.getSelectedItemPosition())).getMaTV());
                obj.setMaSach((sachSpinnerAdapter.getItem(spnSach.getSelectedItemPosition())).getMaSach());
                obj.setTienThue(Integer.parseInt(tien));
                try {
                    obj.setNgay(sdf.parse(ngay));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                obj.setTraSach(spnTraSach.getSelectedItemPosition());
                if(mode){
                    obj.setMaPM(Integer.parseInt(id));
                    fragment.dao.update(obj);
                    Toast.makeText(context, "Dữ liệu đã sửa đổi", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else{
                    fragment.dao.insert(obj);
                    Toast.makeText(context, "Dữ liệu đã thêm ", Toast.LENGTH_SHORT).show();
                    clear();
                }
                fragment.updateAdapter();
            }
        });
    }
    public boolean validate(String str,TextInputLayout layout){
        if(str.isEmpty()){
            layout.setError("Không để trống dữ liệu");
            return false;
        }else{
            layout.setError(null);
            return true;
        }
    }
    public void show(){
        dialog.show();
    }
    public void clear(){
        layoutTien.setError("");
        layoutTien.getEditText().setText("");
        layoutDate.getEditText().setText(sdf.format(new Date()));
    }



}
