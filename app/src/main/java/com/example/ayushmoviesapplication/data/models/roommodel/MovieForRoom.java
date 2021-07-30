package com.example.ayushmoviesapplication.data.models.roommodel;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class MovieForRoom implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int generated_id;



    private int id;
    private String movie_title;
    private float rating;

    public int getId() {
        return id;
    }

    public int getGenerated_id() {
        return generated_id;
    }

    public void setGenerated_id(int generated_id) {
        this.generated_id = generated_id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
