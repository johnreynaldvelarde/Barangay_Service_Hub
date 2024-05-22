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
import com.example.barangayservicehub.getter_class.Get_Call;
import com.example.barangayservicehub.getter_class.Get_RequestFile;
import com.example.barangayservicehub.module.RequestFileViewActivity;
import com.example.barangayservicehub.module.ServicesViewActivity;

import java.util.ArrayList;

public class RequestFileAdapter extends RecyclerView.Adapter<RequestFileAdapter.RequestFileHolder> {

    Context context;
    ArrayList<Get_RequestFile> list;

    public RequestFileAdapter(Context context, ArrayList<Get_RequestFile> listFile) {
        this.context = context;
        this.list = listFile;
    }
    @NonNull
    @Override
    public RequestFileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_file,parent, false);
        return new RequestFileAdapter.RequestFileHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestFileHolder holder, int position) {

        Get_RequestFile file = list.get(position);
        holder.fileName.setText(file.getFileName());

        holder.btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RequestFileViewActivity.class);
                //intent.putExtra("NEWS_TITLE_EXTRA", news.getNewsTitle()); // Example: Passing news title
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class RequestFileHolder extends RecyclerView.ViewHolder {

        TextView fileName;
        ImageButton btnFile;
        public RequestFileHolder(@NonNull View itemView) {
            super(itemView);

            fileName = itemView.findViewById(R.id.listFileName);
            btnFile = itemView.findViewById(R.id.btnFileView);
        }
    }
}
