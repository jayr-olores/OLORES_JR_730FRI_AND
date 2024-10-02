package com.activity.a0927_navigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TodoListFragment extends Fragment {

    private ArrayList<TodoItem> todoList;
    private TodoAdapter adapter;
    private ImageView selectImageView;
    private int selectedImageResource = R.drawable.ic_launcher_foreground;
    private GestureDetector gestureDetector;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        // Initialize views
        ListView todoListView = view.findViewById(R.id.todoListView);
        EditText todoEditText = view.findViewById(R.id.todoEditText);
        Button addButton = view.findViewById(R.id.addButton);
        selectImageView = view.findViewById(R.id.selectImageView);

        todoList = new ArrayList<>();
        adapter = new TodoAdapter(requireContext(), todoList);
        todoListView.setAdapter(adapter);

        gestureDetector = new GestureDetector(requireContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                View childView = getChildViewUnder(todoListView, e.getX(), e.getY());
                if (childView != null) {
                    int position = todoListView.getPositionForView(childView);
                    showEditDeleteDialog(position);
                }
                return super.onDoubleTap(e);
            }
        });

        selectImageView.setImageResource(selectedImageResource);

        selectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog(selectImageView);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = todoEditText.getText().toString();
                if (!newTask.isEmpty()) {
                    todoList.add(new TodoItem(newTask, selectedImageResource));
                    adapter.notifyDataSetChanged();
                    todoEditText.setText("");
                }
            }
        });

        todoListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return false;
            }
        });

        return view;
    }

    private View getChildViewUnder(ListView listView, float x, float y) {
        for (int i = 0; i < listView.getChildCount(); i++) {
            View child = listView.getChildAt(i);
            if (x >= child.getLeft() && x <= child.getRight() &&
                    y >= child.getTop() && y <= child.getBottom()) {
                return child;
            }
        }
        return null;
    }

    private void showEditDeleteDialog(final int position) {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_todo_item, null);
        final EditText editTodoText = dialogView.findViewById(R.id.editTodoText);
        final ImageView editImageView = dialogView.findViewById(R.id.editImageView);

        final TodoItem todoItem = todoList.get(position);
        editTodoText.setText(todoItem.getText());
        editImageView.setImageResource(todoItem.getImageResourceId());

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(dialogView)
                .setTitle("Edit or Delete Item")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoItem.setText(editTodoText.getText().toString());
                        todoItem.setImageResourceId(selectedImageResource);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todoList.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                })
                .create()
                .show();

        editImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageSelectionDialog(editImageView);
            }
        });
    }

    private void showImageSelectionDialog(final ImageView imageViewToUpdate) {

        final int[] imageResources = {
                R.drawable.nayeon, R.drawable.yuna, R.drawable.maloi,
                R.drawable.julie
        };

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_image_selection, null);
        GridView gridView = dialogView.findViewById(R.id.imageGridView);

        ImageAdapter imageAdapter = new ImageAdapter(requireContext(), imageResources);
        gridView.setAdapter(imageAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Select an Image");
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedImageResource = imageResources[position];
                imageViewToUpdate.setImageResource(selectedImageResource);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
