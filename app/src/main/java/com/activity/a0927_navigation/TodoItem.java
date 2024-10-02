package com.activity.a0927_navigation;

public class TodoItem {
    private String text;
    private int imageResourceId;

    public TodoItem(String text, int imageResourceId) {
        this.text = text;
        this.imageResourceId = imageResourceId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
