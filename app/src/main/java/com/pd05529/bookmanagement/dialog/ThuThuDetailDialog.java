package com.pd05529.bookmanagement.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.pd05529.bookmanagement.DAO.ThuThuDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.ThuThu;
import com.pd05529.bookmanagement.ui.ThuThuFragment;

public class ThuThuDetailDialog {
    private LayoutInflater inflater;
    private AlertDialog dialog;
    private ThuThuDAO dao;
    TextView tvId,tvTen,tvPhone,tvMatKhau,tvRole;
    public ThuThuDetailDialog(Context context, ThuThuFragment fragment, ThuThu... args){
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.detail_dialog_thuthu,null);
        tvId = view.findViewById(R.id.tvId);
        tvTen = view.findViewById(R.id.tvTen);
        tvPhone = view.findViewById(R.id.tvLs);
        tvMatKhau = view.findViewById(R.id.tvMatkhau);
        tvRole = view.findViewById(R.id.tvRole);

        dao = new ThuThuDAO(context);

        tvId.setText(args[0].getMaTT());
        tvTen.setText(args[0].getHoTen());
        tvPhone.setText("0"+args[0].getPhone());
        tvMatKhau.setText(args[0].getMatKhau());
        if(dao.getId(args[0].getMaTT()).getRole() ==0){
            tvRole.setText("Admin");
        }else{
         tvRole.setText("Thủ thư");
        }

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
