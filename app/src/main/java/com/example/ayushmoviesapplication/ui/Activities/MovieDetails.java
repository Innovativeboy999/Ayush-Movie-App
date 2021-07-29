package com.example.ayushmoviesapplication.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ayushmoviesapplication.R;

public class MovieDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MovieDetails.this, MainActivity.class);
        intent.putExtra("back_pressed",true);
        startActivity(intent);
    }
}