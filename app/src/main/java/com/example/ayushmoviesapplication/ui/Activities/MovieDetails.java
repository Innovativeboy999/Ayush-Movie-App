package com.example.ayushmoviesapplication.ui.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ayushmoviesapplication.data.models.MovieDetailsResponse.MovieDetailsResponse;
import com.example.ayushmoviesapplication.data.repository.RoomRepository.RoomMoviesRepository;
import com.example.ayushmoviesapplication.databinding.ActivityMovieDetailsBinding;
import com.example.ayushmoviesapplication.ui.Activities.ViewModels.MovieDetailViewModel;

public class MovieDetails extends AppCompatActivity {

    private MovieDetailViewModel viewModel;
    private Intent intent;
    private ActivityMovieDetailsBinding binding;
    private MovieDetailsResponse response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        intent=getIntent();
        viewModel= new ViewModelProvider(this).get(MovieDetailViewModel.class);

        viewModel.fetchMovieDetails(intent.getIntExtra("movie_id",100));


            viewModel.movieDetailsResponseLiveData.observe(this, new Observer<MovieDetailsResponse>() {
                @Override
                public void onChanged(MovieDetailsResponse movieDetailsResponse) {
                    response=movieDetailsResponse;
                    Glide.with(binding.getRoot().getContext()).
                            load("https://image.tmdb.org/t/p/w500/"+movieDetailsResponse.getPosterPath()).into(binding.movieDetailsPic);
                    binding.movieOverviewInDetails.setText(movieDetailsResponse.getOverview());
                }
            });

            binding.addBookmarkInDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.addToRoomDatabse(response.getTitle(),response.getId(),response.getVoteAverage()/2,getApplicationContext());
                    Toast.makeText(MovieDetails.this, "Movie Added to Saved", Toast.LENGTH_SHORT).show();
                }
            });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MovieDetails.this, MainActivity.class);
        intent.putExtra("back_pressed",true);
        startActivity(intent);
    }
}