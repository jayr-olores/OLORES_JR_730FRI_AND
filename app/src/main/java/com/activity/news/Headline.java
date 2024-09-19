package com.activity.news;

public class Headline {
    private final String title;
    private final String date;
    private final String author;
    private final String content;

    public Headline(String title, String date, String author, String content) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
