package com.pd05529.bookmanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.Sach;
import com.pd05529.bookmanagement.ui.SachFragment;

public class SachDetailDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    TextView tvId,tvTen,tvTacgia,tvLs;
    public SachDetailDialog(Context context, SachFragment fragment, Sach... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_dialog_sach,null);
        tvId = view.findViewById(R.id.tvId);
        tvTen = view.findViewById(R.id.tvTen);
        tvTacgia = view.findViewById(R.id.tvTacgia);
        tvLs = view.findViewById(R.id.tvLs);


        tvId.setText(String.valueOf(args[0].getMaSach()));
        tvTen.setText(args[0].getTenSach());
        tvTacgia.setText(args[0].getTacGia());

        tvLs.setText(fragment.loaiSachDAO.getId(String.valueOf(args[0].getMaLoai())).getTenLoai());

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
