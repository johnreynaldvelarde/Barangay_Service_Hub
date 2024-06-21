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
import com.example.barangayservicehub.getter_class.Get_Programs;
import com.example.barangayservicehub.module.ProgramsViewActivity;

import java.util.ArrayList;

public class ProgramsAdapter extends RecyclerView.Adapter<ProgramsAdapter.ServicesHolder> {

    Context context;
    ArrayList<Get_Programs> list;

    public ProgramsAdapter(Context context, ArrayList<Get_Programs> listServices) {
        this.context = context;
        this.list = listServices;
    }

    @NonNull
    @Override
    public ServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_services,parent, false);
        return new ProgramsAdapter.ServicesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesHolder holder, int position) {

        Get_Programs programs = list.get(position);
        holder.programName.setText(programs.getProgramName());
        holder.programDescription.setText(programs.getProgramDescription());


        holder.btnServiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProgramsViewActivity.class);
                intent.putExtra("PROGRAMS_TITLE", programs.getProgramName());
                intent.putExtra("PROGRAMS_DESCRIPTION", programs.getProgramDescription());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ServicesHolder extends RecyclerView.ViewHolder {

        TextView programName,  programDescription;
        ImageButton btnServiceView;

        public ServicesHolder(@NonNull View itemView) {
            super(itemView);
            programName = itemView.findViewById(R.id.listServicesName);
            programDescription = itemView.findViewById(R.id.listServicesDescription);
            btnServiceView = itemView.findViewById(R.id.btnServicesView);
        }
    }
}
