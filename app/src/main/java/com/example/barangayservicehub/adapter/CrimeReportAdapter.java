package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.User;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;

import java.util.ArrayList;

public class CrimeReportAdapter extends RecyclerView.Adapter<CrimeReportAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> list;

    public CrimeReportAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_crime_report_list_pending,parent, false);
        return new CrimeReportAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //Get_CrimeReport reportCrime = list.get(position);
        User user = list.get(position);
        holder.title.setText(user.getName());
        holder.recordTime.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, location, comment, recordTime, imageReportURL;
        ImageView defaultImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.listTextTitle);
            recordTime = itemView.findViewById(R.id.listTextTime);
        }
    }
}
