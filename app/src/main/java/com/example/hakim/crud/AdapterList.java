package com.example.hakim.crud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder> {

    private ArrayList<Murid> dataList;
    Context context;

    public AdapterList(ArrayList<Murid> dataList, Context ctx){
        this.dataList = dataList;
        this.context = ctx;
    }

    @NonNull
    @Override
    public AdapterList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.app_costum, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolder holder, final int position) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, SingleActivity.class);
                i.putExtra(SingleActivity.EXTRA_KEY , dataList.get(position).getId());
                context.startActivity(i);
            }
        });
        holder.nama.setText("Nama : "+dataList.get(position).getNama());
        holder.tgl.setText("Tgl Lahir : "+dataList.get(position).getTgl_lahir());
        holder.jenis_kelamin.setText("Jenis Kelamin : "+dataList.get(position).getJenis_Kelamin());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size(): 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nama, tgl, jenis_kelamin;
        private LinearLayout card;

        public ViewHolder(View itemView) {
            super(itemView);
            card = (LinearLayout)itemView.findViewById(R.id.card);
            nama = (TextView)itemView.findViewById(R.id.nama);
            tgl = (TextView)itemView.findViewById(R.id.tgl_lahir);
            jenis_kelamin = (TextView)itemView.findViewById(R.id.jenis_kelamin);
        }
    }
}
