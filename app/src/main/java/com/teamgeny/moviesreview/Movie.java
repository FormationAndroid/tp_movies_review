package com.teamgeny.moviesreview;

class Movie {

    private long id;
    private String title, commentary;
    private int rate;

    Movie() {}

    Movie(String title, String commentary, int rate) {
        this.title = title;
        this.commentary = commentary;
        this.rate = rate;
    }

    Movie(int id, String title, String commentary, int rate) {
        this.id = id;
        this.title = title;
        this.commentary = commentary;
        this.rate = rate;
    }

    void setId(int id) {
        this.id = id;
    }

    void setTitle(String title) {
        this.title = title;
    }

    void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    long getId() {
        return id;
    }

    String getCommentary() {
        return commentary;
    }

    String getTitle() {
        return title;
    }

    int getRate() {
        return rate;
    }

    void setRate(int rate) {
        this.rate = rate;
    }

}
