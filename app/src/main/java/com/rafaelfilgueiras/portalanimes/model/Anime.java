package com.rafaelfilgueiras.portalanimes.model;

public class Anime {

    private String title;
    private String content;
    private String excerpt;

    public Anime() {
    }

    public Anime(String title, String content, String excerpt) {
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String setExcerpt(String excerpt) {
        this.excerpt = excerpt;
        return excerpt;
    }
}
