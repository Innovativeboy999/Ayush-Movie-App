package com.example.ayushmoviesapplication.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.databinding.ItemViewMoviesCardBinding;
import com.example.ayushmoviesapplication.ui.Activities.MainActivity;
import com.example.ayushmoviesapplication.ui.Activities.MovieDetails;


public class PopularMovieAdapter extends PagedListAdapter<Movie, RecyclerView .ViewHolder>
    {
        public static final int MOVIE_VIEW_TYPE=1;
        public static final int NETWORK_VIEW_TYPE=2;
        private Context context;

        public PopularMovieAdapter( Context context) {
            super(Movie.DIFF_CALLBACK);
            this.context=context;

        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PopularMovieAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_movies_card,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            Movie movie = getItem(position);
            if(holder instanceof PopularMovieAdapterViewHolder)
            {
                ((PopularMovieAdapterViewHolder) holder).bind(movie);
            }
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        class PopularMovieAdapterViewHolder extends RecyclerView.ViewHolder
        {
            private ItemViewMoviesCardBinding binding;
            public PopularMovieAdapterViewHolder(@NonNull View itemView) {
                super(itemView);
                binding=ItemViewMoviesCardBinding.bind(itemView);
            }
            public void bind(Movie movie)
            {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, MovieDetails.class);
                        intent.putExtra("movie_id",movie.getId());
                        context.startActivity(intent);
                    }
                });

                binding.ratingBar.setNumStars(5);
                binding.movieTitle.setText(movie.getTitle());
                binding.ratingBar.setRating(movie.getVoteAverage()/2);
                Glide.with(binding.getRoot().getContext()).load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(binding.movieImg);
            }
        }
    }
