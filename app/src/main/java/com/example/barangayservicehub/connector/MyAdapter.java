package com.example.barangayservicehub.connector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public MyAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_crime_report_list_pending,parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        /*
        Crime_Report report = list.get(position);
        holder.title.setText(report.getTitle());
        holder.recordTime.setText(report.getDateAdded());
        //holder.defaultImage.getDrawable(report.getCrimeImageURL());
         */

        User user = list.get(position);
        holder.title.setText(user.getName());
        holder.recordTime.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, recordTime;
        ImageView defaultImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            //defaultImage = itemView.findViewById(R.id.imageReport);
            title = itemView.findViewById(R.id.listTextTitle);
            recordTime = itemView.findViewById(R.id.listTextTime);
        }
    }
}

