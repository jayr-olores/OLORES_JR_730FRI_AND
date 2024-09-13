package com.activity.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// HeadlineAdapter.java
public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder> {

    private final List<String> headlines;
    private final OnHeadlineClickListener clickListener;

    public interface OnHeadlineClickListener {
        void onHeadlineClick(String headline);
    }

    public HeadlineAdapter(List<String> headlines, OnHeadlineClickListener clickListener) {
        this.headlines = headlines;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_headline, parent, false);
        return new HeadlineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder holder, int position) {
        String headline = headlines.get(position);
        holder.textHeadline.setText(headline);
        holder.itemView.setOnClickListener(v -> clickListener.onHeadlineClick(headline));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    static class HeadlineViewHolder extends RecyclerView.ViewHolder {
        TextView textHeadline;

        HeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            textHeadline = itemView.findViewById(R.id.text_headline);
        }
    }
}

