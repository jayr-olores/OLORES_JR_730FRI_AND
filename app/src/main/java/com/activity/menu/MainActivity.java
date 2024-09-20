package com.activity.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ensure this matches your actual layout file

        ImageButton menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuButton);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                // Use item.getTitle() to compare strings
                String title = item.getTitle().toString();
                if ("Go to Fragment".equals(title)) {
                    // Replace with your fragment navigation logic
                    Toast.makeText(this, "Navigate to Fragment", Toast.LENGTH_SHORT).show();
                    return true;
                } else if ("Show Dialog".equals(title)) {
                    new YourDialogFragment().show(getSupportFragmentManager(), "dialog");
                    return true;
                } else if ("Exit App".equals(title)) {
                    finish(); // Exit the app
                    return true;
                } else {
                    return false; // Default case to handle unexpected titles
                }
            });
            popupMenu.show();
        });
    }
}
