package com.pd05529.bookmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pd05529.bookmanagement.DAO.LoaiSachDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.Sach;

import java.util.List;

public class SachRecyclerViewAdapter extends RecyclerView.Adapter<SachRecyclerViewAdapter.SachViewHolder> {
    private LayoutInflater mInflater;
    private List<Sach> list;
    private LoaiSachDAO dao;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDelClickListener;

    public SachRecyclerViewAdapter(Context context, List<Sach> list){
        this.list = list;
        dao = new LoaiSachDAO(context);
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItemEditClickListener(ItemClickListener itemClickListener){
        SachRecyclerViewAdapter.itemEditClickListener = itemClickListener;
    }
    public void setItemViewClickListener(ItemClickListener itemClickListener){
        SachRecyclerViewAdapter.itemViewClickListener = itemClickListener;
    }

    public void setItemDelClickListener(ItemClickListener itemDelClickListener) {
        SachRecyclerViewAdapter.itemDelClickListener = itemDelClickListener;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_sach,parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        if(list !=null){
            holder.tvTen.setText("Tên sách: "+list.get(position).getTenSach());
            holder.tvTacgia.setText("Tác giả: "+list.get(position).getTacGia());
            holder.tvLS.setText("Thể loại: "+ dao.getId(list.get(position).getMaLoai()+"").getTenLoai());
            holder.itemView.setOnClickListener(v -> {
                if(itemViewClickListener!=null){
                    itemViewClickListener.onItemClick(position);
                }
            });
            holder.imgEdit.setOnClickListener(v -> {
                if(itemEditClickListener!=null){
                    itemEditClickListener.onItemClick(position);
                }
            });
            holder.imgDel.setOnClickListener(v->{
                if(itemDelClickListener!=null){
                    itemDelClickListener.onItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
    }

    public Sach getItem(int position){
        if(list == null || position>= list.size()){
            return null;
        }
        return list.get(position);
    }
    public class SachViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen,tvLS,tvTacgia;
        ImageView imgEdit,imgDel;
        CardView cv;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvLS = (TextView) itemView.findViewById(R.id.tvLs);
            tvTacgia = (TextView) itemView.findViewById(R.id.tvTacgia);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            imgDel = (ImageView) itemView.findViewById(R.id.imgDel);
            cv = (CardView) itemView;
        }
    }
}
