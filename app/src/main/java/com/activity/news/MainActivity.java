package com.activity.news;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_land);

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new HeadlineFragment())
                        .replace(R.id.fragment_container_content, new ContentFragment())
                        .commit();
            }
        } else {
            setContentView(R.layout.activity_main_portrait);

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, new HeadlineFragment())
                        .commit();
            }
        }
    }

}
