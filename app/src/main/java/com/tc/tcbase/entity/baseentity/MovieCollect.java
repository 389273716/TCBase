package com.tc.tcbase.entity.baseentity;

public class MovieCollect {

    private Long id;
    private String movieImage;
    private String title;
    private String year;

    public MovieCollect(Long id, String movieImage, String title, String year) {
        this.id = id;
        this.movieImage = movieImage;
        this.title = title;
        this.year = year;
    }

    public MovieCollect() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovieImage() {
        return this.movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}