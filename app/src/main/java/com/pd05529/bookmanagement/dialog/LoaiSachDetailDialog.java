package com.pd05529.bookmanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.ui.LoaiSachFragment;

public class LoaiSachDetailDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextView tvId,tvTen;
    public LoaiSachDetailDialog(Context context, LoaiSachFragment fragment, LoaiSach... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_dialog_loaisach,null);
        tvId = view.findViewById(R.id.tvId);
        tvTen = view.findViewById(R.id.tvTen);
        tvId.setText(String.valueOf(args[0].getMaLoai()));
        tvTen.setText(args[0].getTenLoai());
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
