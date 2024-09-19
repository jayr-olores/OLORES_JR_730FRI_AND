package com.activity.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String headlineTitle = args.getString("headline_title");
            String date = args.getString("headline_date");
            String author = args.getString("headline_author");
            String contentData = args.getString("headline_content");

            TextView headlineView = view.findViewById(R.id.text_headline);
            TextView dateView = view.findViewById(R.id.text_date);
            TextView authorView = view.findViewById(R.id.text_author);
            TextView contentView = view.findViewById(R.id.text_content);

            if (headlineTitle != null) {
                headlineView.setText(headlineTitle);
            }
            if (date != null) {
                dateView.setText(date);
            }
            if (author != null) {
                authorView.setText(author);
            }
            if (contentData != null) {
                contentView.setText(contentData);
            }
        }

        return view;
    }
}
