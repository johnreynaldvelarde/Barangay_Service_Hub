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
import com.example.barangayservicehub.getter_class.Get_CrimeReport;
import com.example.barangayservicehub.getter_class.Get_News;
import com.example.barangayservicehub.module.NewsViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    Context context;
    ArrayList<Get_News> list;

    private static final long MINUTE_MILLIS = 60 * 1000;
    private static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
    private static final long DAY_MILLIS = 24 * HOUR_MILLIS;

    public NewsAdapter(Context context, ArrayList<Get_News> listNews) {
        this.context = context;
        this.list = listNews;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_news,parent, false);
        return new NewsAdapter.NewsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {

        Get_News news = list.get(position);
        holder.newsTitle.setText(news.getNewsTitle());
        holder.newsArticle.setText(news.getNewsArticle());
        holder.newsTime.setText(formatDate(news.getNewsDateAdded()));
        holder.btnNewsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Start NewsViewActivity and pass necessary data
                Intent intent = new Intent(v.getContext(), NewsViewActivity.class);
                intent.putExtra("NEWS_TITLE_EXTRA", news.getNewsTitle()); // Example: Passing news title
                intent.putExtra("NEWS_DATE_EXTRA", news.getNewsDateAdded());
                intent.putExtra("NEWS_ARTICLE_EXTRA", news.getNewsArticle());
                v.getContext().startActivity(intent);
            }
        });

        if(TextUtils.isEmpty(news.getNewsImageURL())){

            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.barangay_logo)
                    .into(holder.newsImage);
        }
        else{

            Glide.with(holder.itemView.getContext())
                    .load(news.getNewsImageURL())
                    .error(R.drawable.barangay_logo)
                    .into(holder.newsImage);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class NewsHolder extends RecyclerView.ViewHolder {

        TextView newsTitle, newsArticle, newsTime;
        ImageView newsImage;
        ImageButton btnNewsView;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            newsTitle = itemView.findViewById(R.id.listTextTitleNews);
            newsArticle = itemView.findViewById(R.id.listTextArticle);
            newsTime = itemView.findViewById(R.id.listTextNewsTime);
            newsImage = itemView.findViewById(R.id.imageNews);
            btnNewsView = itemView.findViewById(R.id.btnNewsView);

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
    }
}
