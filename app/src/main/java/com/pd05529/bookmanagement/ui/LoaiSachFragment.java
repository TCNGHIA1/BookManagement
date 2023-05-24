package com.pd05529.bookmanagement.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pd05529.bookmanagement.DAO.LoaiSachDAO;
import com.pd05529.bookmanagement.DAO.PhieuMuonDAO;
import com.pd05529.bookmanagement.DAO.SachDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.ItemClickListener;
import com.pd05529.bookmanagement.adapter.LoaiSachRecyclerViewAdapter;
import com.pd05529.bookmanagement.dialog.LoaiSachDetailDialog;
import com.pd05529.bookmanagement.dialog.LoaiSachDialog;
import com.pd05529.bookmanagement.model.LoaiSach;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.Sach;

import java.util.List;

public class LoaiSachFragment extends Fragment {

    public RecyclerView mRv;
    public FloatingActionButton fab;
    public LoaiSachRecyclerViewAdapter adapter;
    public LoaiSachDAO dao;
    public static LoaiSachFragment  newInstance(){return new LoaiSachFragment();}
    public LoaiSachFragment(){}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loaisach,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        dao = new LoaiSachDAO(getActivity());
        updateAdapter();

        LoaiSachFragment currentfragment = this;
        //Thong tin
        adapter.setItemEditClickListener(position -> {
            LoaiSach obj = adapter.getItem(position);
            LoaiSachDialog dialog = new LoaiSachDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Cap nhat
        adapter.setItemViewClickListener(position -> {
            LoaiSach obj = adapter.getItem(position);
            LoaiSachDetailDialog dialog = new LoaiSachDetailDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Them moi
        fab.setOnClickListener(v -> {
            LoaiSachDialog dialog = new LoaiSachDialog(getActivity(),currentfragment);
            dialog.show();
        });

        //Xóa data khi keo sang trai | phai
        adapter.setItemDelClickListener(position -> {
            SachDAO sachDAO = new SachDAO(getActivity());
            PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
            LoaiSach ob = adapter.getItem(position);
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            alert.setMessage("Dữ liệu ảnh hưởng tới phần sách và phiếu mượn.")
                    .setTitle("Bạn có chắc muốn xóa không?")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoaiSach obj = adapter.getItem(position);
                            //Danh sách có loại sách
                            List<Sach> saches = sachDAO.getArrgsLoai(String.valueOf(obj.getMaLoai()));
                            //Xóa những phiếu mượn có sách
                            for (Sach sach: saches) {
                                phieuMuonDAO.deleteSach(String.valueOf(sach.getMaSach()));
                            }
                            //Xóa sách theo mã loại
                                sachDAO.deleteLoai(String.valueOf(ob.getMaLoai()));
                            //Xóa loại sách
                            dao.delete(String.valueOf(obj.getMaLoai()));
                                Toast.makeText(getContext(), "Dữ liệu đã xóa", Toast.LENGTH_SHORT).show();
                                updateAdapter();
                        }
                    })
                    .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alert.create().show();
        });

    }
    public void updateAdapter(){
        adapter = new LoaiSachRecyclerViewAdapter(getActivity(),dao.getAll());
        mRv.setAdapter(adapter);
    }
}