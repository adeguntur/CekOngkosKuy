package com.masyarakat.slamtein.cekongkoskuy.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.masyarakat.slamtein.cekongkoskuy.R;
import com.masyarakat.slamtein.cekongkoskuy.CityActivity;
import com.masyarakat.slamtein.cekongkoskuy.ProvinceActivity;
import com.masyarakat.slamtein.cekongkoskuy.model.Ongkir;

import java.util.List;


public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.OngkirViewHolder>{

    private List<Ongkir> ongkirs;
    private int rowLayout;
    private Context context;
    ProvinceActivity provinceActivity;

    public static class OngkirViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linear_data;
        TextView tvData;



        public OngkirViewHolder(View v) {
            super(v);
            linear_data = v.findViewById(R.id.linear_data);
            tvData = v.findViewById(R.id.tvData);
        }


    }

    public ProvinceAdapter(List<Ongkir> ongkirs, int rowLayout, Context context, ProvinceActivity provinceActivity) {
        this.ongkirs = ongkirs;
        this.rowLayout = rowLayout;
        this.context = context;
        this.provinceActivity = provinceActivity;
    }



    @Override
    public OngkirViewHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new OngkirViewHolder(view);
    }


    @Override
    public void onBindViewHolder(OngkirViewHolder holder, final int position) {
        holder.tvData.setText(ongkirs.get(position).province);



        holder.linear_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), CityActivity.class);
                i.putExtra("province_id", String.valueOf(ongkirs.get(position).province_id));
                i.putExtra("province", String.valueOf(ongkirs.get(position).province));
                i.putExtra("name", provinceActivity.getName());
                i.putExtra("address", provinceActivity.getAddress());
                i.putExtra("telp", provinceActivity.getTelp());
                i.putExtra("bornday", provinceActivity.getBornday());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ongkirs.size();
    }

}