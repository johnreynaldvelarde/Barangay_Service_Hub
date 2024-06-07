package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangayservicehub.R;
import com.example.barangayservicehub.getter_class.Get_Call;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.CallHolder> {

    Context context;
    ArrayList<Get_Call> list;

    public CallAdapter(Context context, ArrayList<Get_Call> listCall) {
        this.context = context;
        this.list = listCall;
    }
    @NonNull
    @Override
    public CallHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_call,parent, false);
        return new CallAdapter.CallHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CallHolder holder, int position) {

        Get_Call call = list.get(position);
        holder.numberName.setText(call.getNumberName());
        holder.contactNumber.setText(call.getContactNumber());

        holder.btnCallNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = call.getContactNumber();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CallHolder extends RecyclerView.ViewHolder {

        TextView numberName, contactNumber;
        ImageButton btnCallNumber;
        public CallHolder(@NonNull View itemView) {
            super(itemView);

            numberName = itemView.findViewById(R.id.listCallName);
            contactNumber = itemView.findViewById(R.id.listContactNumber);
            btnCallNumber = itemView.findViewById(R.id.btnCallView);
        }
    }
}
