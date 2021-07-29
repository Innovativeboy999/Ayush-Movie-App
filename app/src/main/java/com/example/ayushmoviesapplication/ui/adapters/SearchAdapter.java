package com.example.ayushmoviesapplication.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.databinding.ItemViewMoviesCardBinding;
import com.example.ayushmoviesapplication.ui.Activities.MovieDetails;

public class SearchAdapter extends PagedListAdapter<SearchedMovie, RecyclerView .ViewHolder>
{
    public static final int MOVIE_VIEW_TYPE=1;
    public static final int NETWORK_VIEW_TYPE=2;
    private Context context;


   public SearchAdapter( Context context) {
        super(SearchedMovie.DIFF_CALLBACK);
        this.context=context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SearchedMovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_movies_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       SearchedMovie movie = getItem(position);
       if(holder instanceof SearchedMovieViewHolder)
       {
           ((SearchedMovieViewHolder) holder).bind(movie);
       }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class SearchedMovieViewHolder extends RecyclerView.ViewHolder
    {
        private ItemViewMoviesCardBinding binding;
        public SearchedMovieViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemViewMoviesCardBinding.bind(itemView);
        }
        public void bind(SearchedMovie movie)
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
            binding.movieLanguage.setText(movie.getOriginalLanguage());
            binding.ratingBar.setRating(movie.getVoteAverage()/2);
            Glide.with(binding.getRoot().getContext()).load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(binding.movieImg);

        }

    }
}
