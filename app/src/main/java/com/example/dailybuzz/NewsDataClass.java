package com.example.dailybuzz;

public class NewsDataClass {
    String author,content,date,imageUrl,readMoreUrl,time,title;

    public NewsDataClass(String author, String content, String date, String imageUrl, String readMoreUrl, String time, String title) {
        this.author = author;
        this.content = content;
        this.date = date;
        this.imageUrl = imageUrl;
        this.readMoreUrl = readMoreUrl;
        this.time = time;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getReadMoreUrl() {
        return readMoreUrl;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }
}
