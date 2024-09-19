package com.activity.news;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HeadlineFragment extends Fragment {

    private RecyclerView recyclerView;
    private HeadlineAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_headlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String[] headlinesArray = getResources().getStringArray(R.array.headlines_array);
        String[] contentArray = getResources().getStringArray(R.array.content_array);
        String[] datesArray = getResources().getStringArray(R.array.dates_array);
        String[] authorsArray = getResources().getStringArray(R.array.authors_array);

        List<Headline> headlines = new ArrayList<>();
        for (int i = 0; i < headlinesArray.length; i++) {
            headlines.add(new Headline(
                    headlinesArray[i],
                    datesArray[i],
                    authorsArray[i],
                    contentArray[i]
            ));
        }

        adapter = new HeadlineAdapter(headlines, this::onHeadlineClick);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void onHeadlineClick(Headline headline) {
        Bundle args = new Bundle();
        args.putString("headline_title", headline.getTitle());
        args.putString("headline_date", headline.getDate());
        args.putString("headline_author", headline.getAuthor());
        args.putString("headline_content", headline.getContent());

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(args);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_content, contentFragment)
                    .commit();
        } else {
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(args);

            getParentFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment, contentFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
