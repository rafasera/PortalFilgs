package com.rafaelfilgueiras.filgs.model;

public class FilgsAPI {

    private String title;
    private String content;
    private String excerpt;
    private String rating;

    public FilgsAPI() {
    }

    public FilgsAPI(String title, String content, String excerpt, String rating) {
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getRating() {
        return rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String setExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return excerpt;
    }
}
