package com.pd05529.bookmanagement.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pd05529.bookmanagement.DAO.PhieuMuonDAO;
import com.pd05529.bookmanagement.DAO.SachDAO;
import com.pd05529.bookmanagement.DAO.ThanhVienDAO;
import com.pd05529.bookmanagement.DAO.ThuThuDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.ItemClickListener;
import com.pd05529.bookmanagement.adapter.PhieuMuonRecyclerViewAdapter;
import com.pd05529.bookmanagement.dialog.PhieuMuonDetailDialog;
import com.pd05529.bookmanagement.dialog.PhieuMuonDialog;
import com.pd05529.bookmanagement.model.PhieuMuon;


public class PhieuMuonFragment extends Fragment {
    FloatingActionButton fab;
    RecyclerView mRv;
    public PhieuMuonRecyclerViewAdapter adapter;
    public PhieuMuonDAO dao;
    public SachDAO sachDAO;
    public ThanhVienDAO thanhVienDAO;
    public ThuThuDAO thuThuDAO;

    public PhieuMuonFragment() {
        // Required empty public constructor
    }


    public static PhieuMuonFragment newInstance() {
        PhieuMuonFragment fragment = new PhieuMuonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phieumuon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

        dao = new PhieuMuonDAO(getActivity());
        thanhVienDAO = new ThanhVienDAO(getActivity());
        thuThuDAO = new ThuThuDAO(getActivity());
        sachDAO = new SachDAO(getActivity());

        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateAdapter();

        PhieuMuonFragment current = this;
        //Them moi
        fab.setOnClickListener(v -> {
            PhieuMuonDialog dialog = new PhieuMuonDialog(getActivity(),current);
            dialog.show();
        });
        //Xem thong tin
        adapter.setItemViewClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PhieuMuon obj = adapter.getItem(position);
                PhieuMuonDetailDialog dialog = new PhieuMuonDetailDialog(getActivity(),current,obj);
                dialog.show();
            }
        });
        //Cap nhat
        adapter.setItemEditClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PhieuMuon obj = adapter.getItem(position);
                PhieuMuonDialog dialog = new PhieuMuonDialog(getActivity(),current,obj);
                dialog.show();
            }
        });
        //Xoa khi keo trai | phai CardView
        adapter.setItemDelClickListener(position -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Bạn có chắc muốn xóa?")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Xóa phiếu mượn chỉ định
                            dao.delete(String.valueOf(adapter.getItem(position).getMaPM()));
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

    public void updateAdapter() {
        adapter = new PhieuMuonRecyclerViewAdapter(getActivity(),dao.getAll());
        mRv.setAdapter(adapter);
    }
}