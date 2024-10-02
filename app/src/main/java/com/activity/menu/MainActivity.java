package com.activity.menu;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ListView menuList = findViewById(R.id.menu_list);

        String[] menuItems = {
                "1   Item 1", "2   Item 2", "3   Item 3",
                "4   Item 4", "5   Item 5", "6   Item 6",
                "7   Item 7", "8   Item 8", "9   Item 9",
                "10  Item 10"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        menuList.setAdapter(adapter);

        ImageButton menuButton = findViewById(R.id.menu_button);

        menuButton.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, menuButton);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            try {
                Field field = popupMenu.getClass().getDeclaredField("mPopup");
                field.setAccessible(true);
                Object menuHelper = field.get(popupMenu);
                Class<?> classPopupHelper = Class.forName(menuHelper.getClass().getName());
                Method setGravity = classPopupHelper.getMethod("setGravity", int.class);
                setGravity.invoke(menuHelper, Gravity.END); // Try changing this to Gravity.START, CENTER, etc.
            } catch (Exception e) {
                e.printStackTrace();
            }

            popupMenu.setOnMenuItemClickListener(item -> handleMenuItemClick(item, menuButton));
            popupMenu.show();
        });
    }

    private boolean handleMenuItemClick(MenuItem item, View anchor) {
        String title = item.getTitle().toString();
        switch (title) {
            case "Go to Fragment":
                Toast.makeText(this, "Navigate to Fragment", Toast.LENGTH_SHORT).show();
                return true;
            case "Show Dialog":
                new YourDialogFragment().show(getSupportFragmentManager(), "dialog");
                return true;
            case "Exit App":
                showExitPopupMenu(anchor);
                return true;
            default:
                return false;
        }
    }

    private void showExitPopupMenu(View anchor) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View exitPopupView = inflater.inflate(R.layout.popup_exit_confirmation, null);

        final PopupWindow exitPopupWindow = new PopupWindow(exitPopupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        TextView exitConfirm = exitPopupView.findViewById(R.id.txt_exit_confirm);
        exitConfirm.setOnClickListener(v -> {
            finish();
            exitPopupWindow.dismiss();
        });

        exitPopupWindow.showAsDropDown(anchor, 0, 20);
    }
}
