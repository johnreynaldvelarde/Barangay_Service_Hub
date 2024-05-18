package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.getter_class.Get_Stats;

import java.util.ArrayList;

public class BarangayStatsAdapter extends RecyclerView.Adapter<BarangayStatsAdapter.StatsViewHolder> {

    Context context;
    ArrayList<Get_Stats> list;


    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class StatsViewHolder extends RecyclerView.ViewHolder {
        public StatsViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
