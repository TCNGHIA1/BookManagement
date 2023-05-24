package com.pd05529.bookmanagement.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.pd05529.bookmanagement.DAO.SachDAO;
import com.pd05529.bookmanagement.DAO.ThanhVienDAO;
import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.PhieuMuon;

import java.text.SimpleDateFormat;
import java.util.List;

public class PhieuMuonRecyclerViewAdapter extends RecyclerView.Adapter<PhieuMuonRecyclerViewAdapter.PhieuMuonViewHolder>{
    private List<PhieuMuon> list;
    private LayoutInflater inflater;
    private ThanhVienDAO thanhVienDAO;
    private SachDAO sachDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    public static ItemClickListener itemEditClickListener;
    public static ItemClickListener itemViewClickListener;
    public static ItemClickListener itemDelClickListener;

    public PhieuMuonRecyclerViewAdapter(Context context, List<PhieuMuon> list){
        this.list = list;
        thanhVienDAO = new ThanhVienDAO(context);
        sachDAO = new SachDAO(context);
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(List<PhieuMuon> list) {
        this.list = list;
    }

    public List<PhieuMuon> getList() {
        return list;
    }

    public void setItemEditClickListener(ItemClickListener itemClickListener){
        PhieuMuonRecyclerViewAdapter.itemEditClickListener = itemClickListener;
    }
    public void setItemViewClickListener(ItemClickListener itemClickListener){
        PhieuMuonRecyclerViewAdapter.itemViewClickListener = itemClickListener;
    }

    public void setItemDelClickListener(ItemClickListener itemDelClickListener) {
        PhieuMuonRecyclerViewAdapter.itemDelClickListener = itemDelClickListener;
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recyclerview_phieumuon,parent,false);
        PhieuMuonViewHolder viewHolder = new PhieuMuonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (list!=null){
            holder.tvId.setText(String.valueOf(list.get(position).getMaPM()));
            holder.tvName.setText(thanhVienDAO.getId(String.valueOf(list.get(position).getMaTV())).getHoTen());
            holder.tvSach.setText("Tên sách: "+sachDAO.getId(String.valueOf(list.get(position).getMaSach())).getTenSach());
            holder.tvTienThue.setText("Tiền thuê: "+list.get(position).getTienThue() +" VNĐ");
                    holder.tvNgay.setText("Ngày mượn: "+sdf.format(list.get(position).getNgay()));
            if(list.get(position).getTraSach()==0){
                holder.tvTraSach.setText("Đã trả sách");
                holder.tvTraSach.setTextColor(Color.parseColor("#25B24E"));
            }else{
                holder.tvTraSach.setText("Chưa trả sách");
                holder.tvTraSach.setTextColor(Color.parseColor("#FF120C"));
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

    public PhieuMuon getItem(int position){
        if(list == null || position>= list.size()){
            return null;
        }
        return list.get(position);
    }

    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgEdit,imgDel;
        public TextView tvId,tvName,tvSach,tvNgay,tvTraSach,tvTienThue;
        public CardView cv;
        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvName = (TextView) itemView.findViewById(R.id.tvNameTV);
            tvSach = (TextView) itemView.findViewById(R.id.tvSach);
            tvTienThue = (TextView) itemView.findViewById(R.id.tvTienThue);
            tvNgay = (TextView) itemView.findViewById(R.id.tvNgay);
            tvTraSach = (TextView) itemView.findViewById(R.id.tvTraSach);
            imgEdit = (ImageView) itemView.findViewById(R.id.imgEdit);
            imgDel = (ImageView) itemView.findViewById(R.id.imgDel);
            cv = (CardView) itemView;
        }
    }
}
