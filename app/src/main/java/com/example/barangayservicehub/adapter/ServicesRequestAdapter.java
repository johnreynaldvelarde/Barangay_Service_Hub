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
import com.example.barangayservicehub.admin_view.Request_View_Activity;
import com.example.barangayservicehub.getter_class.Get_Programs;
import com.example.barangayservicehub.getter_class.Get_Services_Request;
import com.example.barangayservicehub.module.ProgramsViewActivity;

import java.util.ArrayList;

public class ServicesRequestAdapter extends RecyclerView.Adapter<ServicesRequestAdapter.RequestHolder> {

    Context context;
    ArrayList<Get_Services_Request> list;

    public ServicesRequestAdapter(Context context, ArrayList<Get_Services_Request> listRequest){
        this.context = context;
        this.list = listRequest;
    }

    @NonNull
    @Override
    public RequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_service_request,parent, false);
       return new ServicesRequestAdapter.RequestHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestHolder holder, int position) {

        Get_Services_Request servicesRequest = list.get(position);
        holder.requestName.setText(servicesRequest.getRequestName());
        holder.requestPurpose.setText(servicesRequest.getRequestPurpose());

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(v.getContext(), Request_View_Activity.class);
                 intent.putExtra("REQUEST_NAME", servicesRequest.getRequestName());
                 intent.putExtra("REQUEST_PURPOSE", servicesRequest.getRequestPurpose());
                 v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class RequestHolder extends RecyclerView.ViewHolder{

        TextView requestName, requestPurpose;
        ImageButton btnView;

        public RequestHolder(@NonNull View itemView) {
            super(itemView);

            requestName = itemView.findViewById(R.id.listRequestName);
            requestPurpose = itemView.findViewById(R.id.listRequestPurpose);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
