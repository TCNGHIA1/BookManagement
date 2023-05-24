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
import com.pd05529.bookmanagement.model.ThanhVien;

import java.text.SimpleDateFormat;
import java.util.List;

public class ThanhVienRecyclerViewAdapter extends RecyclerView.Adapter<ThanhVienRecyclerViewAdapter.ThanhVienViewHolder>{
    private LayoutInflater mInflater;
    private List<ThanhVien> list;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDelClickListener;

    public ThanhVienRecyclerViewAdapter(Context context, List<ThanhVien> list){
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItemEditClickListener(ItemClickListener itemClickListener){
        ThanhVienRecyclerViewAdapter.itemEditClickListener = itemClickListener;
    }
    public void setItemViewClickListener(ItemClickListener itemClickListener){
        ThanhVienRecyclerViewAdapter.itemViewClickListener = itemClickListener;
    }

    public void setItemDelClickListener(ItemClickListener itemDelClickListener) {
        ThanhVienRecyclerViewAdapter.itemDelClickListener = itemDelClickListener;
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_thanhvien,parent,false);
        ThanhVienViewHolder viewHolder = new ThanhVienViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(list !=null){
            holder.tvTen.setText(list.get(position).getHoTen());
            holder.tvNamSinh.setText("Ngày sinh: "+sdf.format(list.get(position).getNamSinh()));
            holder.tvPhone.setText("Số điện thoại: 0"+list.get(position).getPhone());
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

    public ThanhVien getItem(int position){
        if(list == null || position>= list.size()){
            return null;
        }
        return list.get(position);
    }

    public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgEdit,imgDel;
        public TextView tvTen,tvNamSinh,tvPhone;
        public CardView cv;
        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvNamSinh = (TextView) itemView.findViewById(R.id.tvTacgia);
            tvPhone = (TextView) itemView.findViewById(R.id.tvLs);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            imgDel = (ImageView) itemView.findViewById(R.id.imgDel);
            cv = (CardView) itemView;
        }
    }
}
