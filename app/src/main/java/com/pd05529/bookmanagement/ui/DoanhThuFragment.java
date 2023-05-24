package com.pd05529.bookmanagement.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pd05529.bookmanagement.DAO.ThongkeDAO;
import com.pd05529.bookmanagement.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DoanhThuFragment extends Fragment {
    public EditText edNgayBd,edNgayKt;
    public Button btnBd,btnKt,btnDoanhThu;
    public TextView tvDoanhThu;
    public ThongkeDAO dao;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mDay,mMonth;
    public DoanhThuFragment() {
        // Required empty public constructor
    }

    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();

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
        return inflater.inflate(R.layout.fragment_doanhthu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edNgayBd = view.findViewById(R.id.edNgayBd);
        edNgayKt = view.findViewById(R.id.edNgayKt);
        btnBd = view.findViewById(R.id.btnBd);
        btnKt = view.findViewById(R.id.btnKt);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        dao = new ThongkeDAO(getActivity());
        edNgayKt.setEnabled(false);
        edNgayBd.setEnabled(false);
        //Chọn ngày Bắt đầu
        btnBd.setOnClickListener(v ->{
            showDialog(edNgayBd);
        });
        btnKt.setOnClickListener(v ->{
            showDialog(edNgayKt);
        });

        btnDoanhThu.setOnClickListener(v->{
            countDoanhThu();
        });
    }

    private void countDoanhThu() {
        if (check()!=1){
            return;
        }
        int doanhthu = dao.getDoanhThu(edNgayBd.getText().toString(),edNgayKt.getText().toString());
        tvDoanhThu.setText(doanhthu +" VND");
    }

    //Hiển thị Dialog chọn ngày
    private void showDialog(EditText editText) {
        Calendar mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                0, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                GregorianCalendar c = new GregorianCalendar(year,month,dayOfMonth);
                editText.setText(sdf.format(c.getTime()));
            }
        }, mYear, mMonth, mDay);
        dialog.show();
    }

    //Kiểm tra ngay nhập
    public int check(){
        String strBd = edNgayBd.getText().toString();
        String strKt = edNgayKt.getText().toString();

        if(strBd.trim().isEmpty()){
            edNgayBd.setError("Vui lòng nhập dữ liệu");
            return -1;
        }
        if(strKt.trim().isEmpty()){
            edNgayKt.setError("Vui lòng nhập dữ liệu");
            return -1;
        }
        return 1;
    }

}