package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.getter_class.Get_Services;
import com.example.barangayservicehub.module.NewsViewActivity;
import com.example.barangayservicehub.module.ServicesViewActivity;

import java.util.ArrayList;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesHolder> {

    Context context;
    ArrayList<Get_Services> list;

    public ServicesAdapter(Context context, ArrayList<Get_Services> listServices) {
        this.context = context;
        this.list = listServices;
    }

    @NonNull
    @Override
    public ServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_services,parent, false);
        return new ServicesAdapter.ServicesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesHolder holder, int position) {

        Get_Services services = list.get(position);
        holder.serviceName.setText(services.getServiceName());
        holder.serviceDescription.setText(services.getServiceDescription());


        holder.btnServiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ServicesViewActivity.class);
                //intent.putExtra("NEWS_TITLE_EXTRA", news.getNewsTitle()); // Example: Passing news title
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ServicesHolder extends RecyclerView.ViewHolder {

        TextView serviceName,  serviceDescription;
        ImageButton btnServiceView;

        public ServicesHolder(@NonNull View itemView) {
            super(itemView);
            serviceName = itemView.findViewById(R.id.listServicesName);
            serviceDescription = itemView.findViewById(R.id.listServicesDescription);
            btnServiceView = itemView.findViewById(R.id.btnServicesView);

        }
    }
}
