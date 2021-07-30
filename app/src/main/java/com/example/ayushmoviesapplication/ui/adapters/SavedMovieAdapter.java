package com.example.ayushmoviesapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;
import com.example.ayushmoviesapplication.databinding.ItemViewMoviesCardBinding;

import java.util.List;

public class SavedMovieAdapter  extends RecyclerView.Adapter<SavedMovieAdapter.ViewHolder>
{
    private List<MovieForRoom> dataMovies;
    public SavedMovieAdapter(List<MovieForRoom> dataMovies)
    {
        this.dataMovies=dataMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_movies_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieForRoom movie = dataMovies.get(position);
        ((ViewHolder) holder).bind(movie);
    }

    @Override
    public int getItemCount() {
        return dataMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private ItemViewMoviesCardBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemViewMoviesCardBinding.bind(itemView);
        }

        public void bind(MovieForRoom movie)
        {
            binding.movieTitle.setText(movie.getMovie_title());
            binding.ratingBar.setRating(movie.getRating());
            binding.movieImg.setVisibility(View.INVISIBLE);
            binding.removeBookmarkInDetails.setVisibility(View.VISIBLE);
            binding.textImgPlaceholder.setVisibility(View.VISIBLE);
        }

    }
}
