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
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.pd05529.bookmanagement.DAO.PhieuMuonDAO;
import com.pd05529.bookmanagement.DAO.ThuThuDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.ThuThuRecyclerViewAdapter;
import com.pd05529.bookmanagement.dialog.ThuThuDetailDialog;
import com.pd05529.bookmanagement.dialog.ThuThuDialog;
import com.pd05529.bookmanagement.model.PhieuMuon;
import com.pd05529.bookmanagement.model.ThuThu;

import java.util.List;


public class ThuThuFragment extends Fragment {


    public RecyclerView mRv;
    public FloatingActionButton fab;
    public ThuThuRecyclerViewAdapter adapter;
    public ThuThuDAO dao;
    public static ThuThuFragment  newInstance(){return new ThuThuFragment();}
    public ThuThuFragment(){}
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thuthu,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRv = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        dao = new ThuThuDAO(getActivity());
        mRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateAdapter();

        ThuThuFragment currentfragment = this;
        //Hien Dialog thong tin
        adapter.setItemEditClickListener(position -> {
            ThuThu obj = adapter.getItem(position);
            ThuThuDialog dialog = new ThuThuDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Hien Dialog sua chua
        adapter.setItemViewClickListener(position -> {
            ThuThu obj = adapter.getItem(position);
            ThuThuDetailDialog dialog = new ThuThuDetailDialog(getActivity(),currentfragment,obj);
            dialog.show();
        });
        //Su kien nut FloatingActionButton
        fab.setOnClickListener(v -> {
            ThuThuDialog dialog = new ThuThuDialog(getActivity(),currentfragment);
            dialog.show();
        });
        //Xóa data khi keo sang trai | phai CardView
        adapter.setItemDelClickListener(position -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Bạn có chắc muốn xóa?")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ThuThu ob =adapter.getItem(position);
                            //Kiểm tra thủ thư tồn tại trong phiếu mượn
                            PhieuMuonDAO phieuMuonDAO = new PhieuMuonDAO(getContext());
                            List<PhieuMuon> phieuMuons =  phieuMuonDAO.getData(
                                    "SELECT * FROM PhieuMuon WHERE maTT=?",ob.getMaTT()
                            );
                            if(phieuMuons.size()>0){
                                Toast.makeText(getContext(), "Tồn tại phiếu mượn có mã: "+ob.getMaTT()+"\n Không thể xóa", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            dao.delete(String.valueOf(ob.getMaTT()));
                            updateAdapter();
                            Toast.makeText(getContext(), "Dữ liệu đã xóa", Toast.LENGTH_SHORT).show();
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
        adapter = new ThuThuRecyclerViewAdapter(getContext(),dao.getAll());
        mRv.setAdapter(adapter);
    }
}