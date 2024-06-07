package com.example.barangayservicehub.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.barangayservicehub.R;
import com.example.barangayservicehub.connector.User;
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.module.ReportViewActivity;
import com.example.barangayservicehub.module.ServicesViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CrimeReportAdapter extends RecyclerView.Adapter<CrimeReportAdapter.MyViewHolder> {

    Context context;
    ArrayList<Get_CrimeReport> list;

    private static final long MINUTE_MILLIS = 60 * 1000;
    private static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    public CrimeReportAdapter(Context context, ArrayList<Get_CrimeReport> list) {
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

        Get_CrimeReport report = list.get(position);
        holder.title.setText(report.getTitle());
        holder.dateAdded.setText(formatDate(report.getDateAdded()));


        if (TextUtils.isEmpty(report.getCrimeImageURL())) {

            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barangay_logo)
                    .into(holder.imageReport);
        } else {

            Glide.with(holder.itemView.getContext())
                    .load(report.getCrimeImageURL())
                    .error(R.drawable.barangay_logo)
                    .into(holder.imageReport);
        }

        holder.btnReportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReportViewActivity.class);
                intent.putExtra("REPORT_TITLE", report.getTitle());
                intent.putExtra("REPORT_DATE", report.getDateAdded());
                intent.putExtra("REPORT_IMAGE_URL", report.getCrimeImageURL());
                intent.putExtra("REPORT_COMMENT", report.getComment());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        String reportID, userID, reportTitle, reportComment, reportDate, imageReportURL;
        TextView title, dateAdded;

        ImageView imageReport;
        ImageButton btnReportView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.listTextTitle);
            dateAdded = itemView.findViewById(R.id.listTextTime);
            imageReport = itemView.findViewById(R.id.imageReport);
            imageReport.setScaleType(ImageView.ScaleType.CENTER_CROP);
            btnReportView = itemView.findViewById(R.id.btnReportView);
        }
    }

    private String formatDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = format.parse(dateString);
            return getTimeAgo(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return dateString;
        }
    }

    private String getTimeAgo(long time) {
        long now = System.currentTimeMillis();
        final long diff = now - time;
        if (diff < 0) {
            return "Just now";
        } else if (diff < MINUTE_MILLIS) {
            return "Just now";
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "A minute ago";
        } else if (diff < 50 * MINUTE_MILLIS) {
            return diff / MINUTE_MILLIS + " minutes ago";
        } else if (diff < 90 * MINUTE_MILLIS) {
            return "An hour ago";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " hours ago";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "Yesterday";
        } else {
            return diff / DAY_MILLIS + " days ago";
        }

        /*
        holder.reportID = report.getGetReportID();
        holder.userID = report.getGetUserID();
        holder.reportTitle = report.getGetTitle();
        holder.reportComment = report.getGetComment();
        holder.reportDate = report.getGetDate();
        holder.imageReportURL = report.getGetReportImageURL();

         */
    }
}
