package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.getter_class.Get_Stats;

import java.util.ArrayList;

public class BarangayStatsAdapter extends RecyclerView.Adapter<BarangayStatsAdapter.StatsViewHolder> {

    Context context;
    ArrayList<Get_Stats> list;

    public BarangayStatsAdapter(Context context, ArrayList<Get_Stats> listStats) {
        this.context = context;
        this.list = listStats;
    }


    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_barangay_stats,parent, false);
        return new BarangayStatsAdapter.StatsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {

        Get_Stats stats = list.get(position);
        holder.textStatsName.setText(stats.getStatsName());
        holder.textStatsNumber.setText(stats.getStatsNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class StatsViewHolder extends RecyclerView.ViewHolder {

        TextView textStatsName, textStatsNumber;

        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);

            textStatsName = itemView.findViewById(R.id.textStatsName);
            textStatsNumber = itemView.findViewById(R.id.textStatsNumber);
        }
    }
}
