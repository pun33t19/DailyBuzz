package com.example.dailybuzz.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dailybuzz.NewsDataClass;
import com.example.dailybuzz.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewsItems extends RecyclerView.Adapter<NewsItems.NewsItemViewHolder> {
    Context context;
    List<NewsDataClass> newsList=new ArrayList<>();

    public NewsItems(Context context, List<NewsDataClass> newsList){
        this.context=context;
        this.newsList=newsList;
    }
    @Override
    public NewsItemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_display,parent,false);

        return new NewsItemViewHolder(view);
    }


    public void onBindViewHolder(@NonNull @NotNull NewsItemViewHolder holder, int position) {
        NewsDataClass newsDataClass=newsList.get(position);

        Glide.with(holder.itemView).load(newsDataClass.getImageUrl()).into(holder.newsImage);
        holder.headline.setText(newsDataClass.getTitle());
        holder.headline2.setText(newsDataClass.getAuthor()+","+newsDataClass.getDate()+newsDataClass.getTime());
        holder.news.setText(newsDataClass.getContent());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsItemViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        TextView headline;
        TextView headline2;
        TextView news;
       public NewsItemViewHolder(@NonNull @NotNull View itemView) {
           super(itemView);

           newsImage=itemView.findViewById(R.id.news_image);
           headline=itemView.findViewById(R.id.headline_text_view);
           headline2=itemView.findViewById(R.id.news_headline2_text_view);
           news=itemView.findViewById(R.id.news_text_view);
       }
   }
}
