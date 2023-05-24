package com.pd05529.bookmanagement.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.ThuThu;
import com.pd05529.bookmanagement.ui.PhieuMuonFragment;

import java.text.SimpleDateFormat;

public class PhieuMuonDetailDialog {
    public AlertDialog dialog;

    public TextView tvId,tvTenTv,tvTenSach,tvTienThue,tvThuThu,tvNgayThue,tvTraSach,tvIdTT;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public PhieuMuonDetailDialog(Context context, PhieuMuonFragment fragment, PhieuMuon... args){
        View view = LayoutInflater.from(context).inflate(R.layout.detail_dialog_phieumuon,null);
        tvId = view.findViewById(R.id.tvId);
        tvTenTv = view.findViewById(R.id.tvTenTV);
        tvTenSach = view.findViewById(R.id.tvTenSach);
        tvTienThue = view.findViewById(R.id.tvTienThue);
        tvThuThu = view.findViewById(R.id.tvThuThu);
        tvNgayThue = view.findViewById(R.id.tvNgayThue);
        tvTraSach = view.findViewById(R.id.tvTraSach);
        tvIdTT = view.findViewById(R.id.tvIdTT);

        tvId.setText(String.valueOf(args[0].getMaPM()));
        tvIdTT.append(",("+args[0].getMaTT()+")");
        tvTenTv.setText(fragment.thanhVienDAO.getId(String.valueOf(args[0].getMaTV())).getHoTen());
        tvTenSach.setText(fragment.sachDAO.getId(String.valueOf(args[0].getMaSach())).getTenSach());
        tvTienThue.setText(String.valueOf(args[0].getTienThue())+" VNĐ");
        tvThuThu.setText(fragment.thuThuDAO.getId(args[0].getMaTT()).getHoTen());
        tvNgayThue.setText(sdf.format(args[0].getNgay()));
        if(args[0].getTraSach()==0){
            tvTraSach.setText("Đã trả sách");
        }else{
            tvTraSach.setText("Chưa trả sách");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view)
                .setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
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
