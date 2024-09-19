// ContentFragment.java
package com.activity.news;
// Import necessary packages
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
            String headlineData = args.getString("headline_key");
            TextView contentView = view.findViewById(R.id.text_content);
            contentView.setText(headlineData);
        }

        return view;
    }
}
