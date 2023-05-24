package com.pd05529.bookmanagement.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.LoaiSach;

import java.util.List;

public class LoaiSachRecyclerViewAdapter extends RecyclerView.Adapter<LoaiSachRecyclerViewAdapter.LoaiSachViewHolder>{
    private LayoutInflater mInflater;
    private List<LoaiSach> list;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDelClickListener;

    public LoaiSachRecyclerViewAdapter(Context context,List<LoaiSach> list){
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItemEditClickListener(ItemClickListener itemClickListener){
        LoaiSachRecyclerViewAdapter.itemEditClickListener = itemClickListener;
    }
    public void setItemViewClickListener(ItemClickListener itemClickListener){
        LoaiSachRecyclerViewAdapter.itemViewClickListener = itemClickListener;
    }

    public void setItemDelClickListener(ItemClickListener itemDelClickListener) {
        LoaiSachRecyclerViewAdapter.itemDelClickListener = itemDelClickListener;
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_loaisach,parent,false);
        LoaiSachViewHolder viewHolder = new LoaiSachViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(list !=null){
            holder.tvTenLoai.setText("Loại sách: "+list.get(position).getTenLoai());
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

    public LoaiSach getItem(int position){
        if(list == null || position>= list.size()){
            return null;
        }
        return list.get(position);
    }

    public void setList(List<LoaiSach> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgEdit,imgDel;
        public TextView tvTenLoai;
        public CardView cv;
        public int ps;
        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLoai = (TextView) itemView.findViewById(R.id.tvTen);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            imgDel = (ImageView) itemView.findViewById(R.id.imgDel);
            cv = (CardView) itemView;
        }
    }
}
