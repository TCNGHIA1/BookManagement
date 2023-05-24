package com.pd05529.bookmanagement.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pd05529.bookmanagement.DAO.ThongkeDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.adapter.TopListViewAdapter;


public class Top10Fragment extends Fragment {
    public TopListViewAdapter adapter;
    public ListView lvTop;
    public ThongkeDAO dao;
    public Top10Fragment() {
        // Required empty public constructor
    }


    public static Top10Fragment newInstance() {
        Top10Fragment fragment = new Top10Fragment();
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
        return inflater.inflate(R.layout.fragment_top10, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lvTop = view.findViewById(R.id.lvTop);
        adapter = new TopListViewAdapter(getActivity());
        dao = new ThongkeDAO(getActivity());
        setLvTop();


    }

    private void setLvTop() {
        adapter.setList(dao.getTop());
        lvTop.setAdapter(adapter);
    }
}