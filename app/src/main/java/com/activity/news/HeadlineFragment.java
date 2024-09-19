package com.activity.news;
// Import necessary packages
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class HeadlineFragment extends Fragment {

    private NavController navController;
    private RecyclerView recyclerView;
    private HeadlineAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_headlines);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        List<String> headlines = Arrays.asList(
                "What is Lorem Ipsum?",
                "Why do we use it?",
                "Where does it come from?"
        );

        adapter = new HeadlineAdapter(headlines, this::onHeadlineClick);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void onHeadlineClick(String headline) {
        Bundle args = new Bundle();
        args.putString("headline_key", headline);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape mode, show content in the same activity
            FragmentManager fragmentManager = getParentFragmentManager();
            ContentFragment contentFragment = new ContentFragment();
            contentFragment.setArguments(args);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_container, contentFragment)
                    .commit();
        } else {
            // In portrait mode, navigate to the ContentFragment
            navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.action_headlineFragment_to_contentFragment, args);
        }
    }
}


