package com.activity.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HeadlineAdapter extends RecyclerView.Adapter<HeadlineAdapter.HeadlineViewHolder> {

    private final List<Headline> headlines;
    private final OnHeadlineClickListener clickListener;

    public interface OnHeadlineClickListener {
        void onHeadlineClick(Headline headline);
    }

    public HeadlineAdapter(List<Headline> headlines, OnHeadlineClickListener clickListener) {
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
        Headline headline = headlines.get(position);
        holder.textHeadline.setText(headline.getTitle());
        holder.textDate.setText(headline.getDate());
        holder.textAuthor.setText(headline.getAuthor());
        holder.itemView.setOnClickListener(v -> clickListener.onHeadlineClick(headline));
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }

    static class HeadlineViewHolder extends RecyclerView.ViewHolder {
        TextView textHeadline;
        TextView textDate;
        TextView textAuthor;

        HeadlineViewHolder(@NonNull View itemView) {
            super(itemView);
            textHeadline = itemView.findViewById(R.id.text_headline);
            textDate = itemView.findViewById(R.id.text_date);
            textAuthor = itemView.findViewById(R.id.text_author);
        }
    }
}
