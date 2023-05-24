package com.pd05529.bookmanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.ThanhVien;
import com.pd05529.bookmanagement.ui.ThanhVienFragment;

import java.text.SimpleDateFormat;

public class ThanhVienDetailDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextView tvId,tvTen,tvNamSinh,tvPhone;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public ThanhVienDetailDialog(Context context, ThanhVienFragment fragment, ThanhVien... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_dialog_thanhvien,null);
        tvId = view.findViewById(R.id.tvId);
        tvTen = view.findViewById(R.id.tvTen);
        tvNamSinh = view.findViewById(R.id.tvTacgia);
        tvPhone = view.findViewById(R.id.tvLs);
        
        tvId.setText(String.valueOf(args[0].getMaTV()));
        tvTen.setText(args[0].getHoTen());
        tvNamSinh.setText(sdf.format(args[0].getNamSinh()));
        tvPhone.setText("0"+args[0].getPhone());
        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        dialog = builder.create();
    }
    public void show(){
        dialog.show();
    }
}
