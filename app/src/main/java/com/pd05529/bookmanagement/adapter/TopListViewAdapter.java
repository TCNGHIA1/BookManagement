package com.pd05529.bookmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pd05529.bookmanagement.R;
import com.pd05529.bookmanagement.model.Top;

import java.util.List;

public class TopListViewAdapter extends BaseAdapter {
    public LayoutInflater inflater;
    public List<Top> list ;
    public TopListViewAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    public List<Top> getList() {
        return list;
    }

    public void setList(List<Top> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class TopViewHolder{
        TextView tvTopName,tvTopSl,tvTopId;
        public TopViewHolder(View view){
            tvTopName = view.findViewById(R.id.tvTopName);
            tvTopSl = view.findViewById(R.id.tvTopSl);
            tvTopId = view.findViewById(R.id.tvTopId);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TopViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.listview_top_item,null,false);
            holder = new TopViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder =(TopViewHolder) convertView.getTag();
        }
        holder.tvTopSl.setText(String.valueOf(list.get(position).getSoLuong()));
        holder.tvTopName.setText(list.get(position).getTenSach());
        holder.tvTopId.setText((position+1)+".");
        return convertView;
    }
}
