package com.activity.a0927_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TodoAdapter extends ArrayAdapter<TodoItem> {

    public TodoAdapter(Context context, List<TodoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        CheckBox checkBox = convertView.findViewById(R.id.itemCheckBox);
        ImageView imageView = convertView.findViewById(R.id.itemImageView);
        TextView textView = convertView.findViewById(R.id.itemTextView);

        textView.setText(item.getText());
        imageView.setImageResource(item.getImageResourceId());

        return convertView;
    }
}
