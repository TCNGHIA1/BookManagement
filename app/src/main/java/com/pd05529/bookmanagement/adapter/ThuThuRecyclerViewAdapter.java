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

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.ThuThu;

import java.util.List;

public class ThuThuRecyclerViewAdapter extends RecyclerView.Adapter<ThuThuRecyclerViewAdapter.ThuThuViewHolder> {
    private LayoutInflater mInflater;
    private List<ThuThu> list;
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDelClickListener;

    public ThuThuRecyclerViewAdapter(Context context, List<ThuThu> list){
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setItemEditClickListener(ItemClickListener itemClickListener){
        ThuThuRecyclerViewAdapter.itemEditClickListener = itemClickListener;
    }
    public void setItemViewClickListener(ItemClickListener itemClickListener){
        ThuThuRecyclerViewAdapter.itemViewClickListener = itemClickListener;
    }
    public void setItemDelClickListener(ItemClickListener itemDelClickListener) {
        ThuThuRecyclerViewAdapter.itemDelClickListener = itemDelClickListener;
    }


    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_thuthu,parent,false);
        return new ThuThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, int position) {
        if(list !=null){
            holder.tvTen.setText(list.get(position).getHoTen());
            holder.tvPhone.setText("Điện thoại: 0"+list.get(position).getPhone());
            holder.tvMa.setText("Mã thủ thư: "+list.get(position).getMaTT());
            if(list.get(position).getRole()==0){
                holder.tvRole.setText("Admin");
            }else{
                holder.tvRole.setText("Thủ thư");
            }
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

    public ThuThu getItem(int position){
        if(list == null || position>= list.size()){
            return null;
        }
        return list.get(position);
    }
    public class ThuThuViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen,tvMa,tvPhone,tvRole;
        ImageView imgEdit,imgDel;
        CardView cv;
        public ThuThuViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvMa = (TextView) itemView.findViewById(R.id.tvMa);
            tvPhone = (TextView) itemView.findViewById(R.id.tvLs);
            tvRole = (TextView) itemView.findViewById(R.id.tvRole);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            imgDel = (ImageView) itemView.findViewById(R.id.imgDel);
            cv = (CardView) itemView;
        }
    }
}
