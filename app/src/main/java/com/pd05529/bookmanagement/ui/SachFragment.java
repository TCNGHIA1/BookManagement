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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pd05529.bookmanagement.DAO.LoaiSachDAO;
import com.pd05529.bookmanagement.DAO.PhieuMuonDAO;
import com.pd05529.bookmanagement.DAO.SachDAO;
import com.pd05529.bookmanagement.DAO.SachDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.SachRecyclerViewAdapter;
import com.pd05529.bookmanagement.adapter.SachRecyclerViewAdapter;
import com.pd05529.bookmanagement.dialog.SachDetailDialog;
import com.pd05529.bookmanagement.dialog.SachDialog;
import com.pd05529.bookmanagement.model.Sach;

import java.util.List;


public class SachFragment extends Fragment {

    public RecyclerView mRv;
    public FloatingActionButton fab;
    public SachRecyclerViewAdapter adapter;
    public static SachDAO dao;
    public LoaiSachDAO loaiSachDAO;
    public static SachFragment  newInstance(){return new SachFragment();}
    public SachFragment(){}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sach,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        dao = new SachDAO(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateAdapter();

        loaiSachDAO = new LoaiSachDAO(getActivity());
        SachFragment currentfragment = this;
        //Hien Dialog thong tin
        adapter.setItemEditClickListener(position -> {
            Sach obj = adapter.getItem(position);
            SachDialog dialog = new SachDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Hien Dialog sua chua
        adapter.setItemViewClickListener(position -> {
            Sach obj = adapter.getItem(position);
            SachDetailDialog dialog = new SachDetailDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Su kien nut FloatingActionButton
        fab.setOnClickListener(v -> {
            SachDialog dialog = new SachDialog(getActivity(),currentfragment);
            dialog.show();
        });
        //Xóa data khi keo sang trai | phai
        adapter.setItemDelClickListener(position -> {
            PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getActivity());
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Bạn có chắc muốn xóa?")
                    .setMessage("Ảnh hưởng đến bảng phiếu mượn.")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Xóa phiếu mượn có sách
                            phieuMuonDAO.deleteSach(String.valueOf(adapter.getItem(position).getMaSach()));
                            //Xóa sách chỉ định
                            dao.delete(String.valueOf(adapter.getItem(position).getMaSach()));
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
            builder.create().show();
        });
    }
    public void updateAdapter(){
        adapter = new SachRecyclerViewAdapter(getActivity(),dao.getAll());
        mRv.setAdapter(adapter);
    }
}