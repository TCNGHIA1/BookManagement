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
import com.pd05529.bookmanagement.DAO.PhieuMuonDAO;
import com.pd05529.bookmanagement.DAO.ThanhVienDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.ThanhVienRecyclerViewAdapter;
import com.pd05529.bookmanagement.adapter.ThanhVienRecyclerViewAdapter;
import com.pd05529.bookmanagement.dialog.ThanhVienDetailDialog;
import com.pd05529.bookmanagement.dialog.ThanhVienDialog;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.ThanhVien;

import java.util.List;

public class ThanhVienFragment extends Fragment {
    public RecyclerView mRv;
    public FloatingActionButton fab;
    public ThanhVienRecyclerViewAdapter adapter;
    public ThanhVienDAO dao;
    public ThanhVienFragment() {
        // Required empty public constructor
    }


    public static ThanhVienFragment newInstance() {
        ThanhVienFragment fragment = new ThanhVienFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thanhvien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        dao = new ThanhVienDAO(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateAdapter();
        ThanhVienFragment currentfragment = this;
        //Hien Dialog thong tin
        adapter.setItemEditClickListener(position -> {
            ThanhVien obj = adapter.getItem(position);
            ThanhVienDialog dialog = new ThanhVienDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Hien Dialog sua chua
        adapter.setItemViewClickListener(position -> {
            ThanhVien obj = adapter.getItem(position);
            ThanhVienDetailDialog dialog = new ThanhVienDetailDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Su kien nut FloatingActionButton
        fab.setOnClickListener(v -> {
            ThanhVienDialog dialog = new ThanhVienDialog(getActivity(),currentfragment);
            dialog.show();
        });

        //Xóa data khi keo sang trai | phai
        adapter.setItemDelClickListener(position -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Bạn có chắc muốn xóa?")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ThanhVien ob =adapter.getItem(position);

                            //Kiểm tra phiếu mượn tồn tại thành viên
                            PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
                            List<PhieuMuon> phieuMuons =  phieuMuonDAO.getData(
                                    "SELECT * FROM PhieuMuon WHERE maTV=?",String.valueOf(ob.getMaTV())
                            );
                            if(phieuMuons.size()>0){
                                Toast.makeText(getContext(), "Tồn tại phiếu mượn có mã: "+ob.getMaTV()+"\n Không thể xóa", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            dao.delete(String.valueOf(ob.getMaTV()));
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
        adapter = new ThanhVienRecyclerViewAdapter(getContext(),dao.getAll());
        mRv.setAdapter(adapter);
    }
}