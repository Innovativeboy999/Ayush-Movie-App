package com.example.ayushmoviesapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ayushmoviesapplication.R;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;
import com.example.ayushmoviesapplication.data.repository.RoomRepository.RoomMoviesRepository;
import com.example.ayushmoviesapplication.databinding.ItemViewMoviesCardBinding;

import java.util.List;

public class SavedMovieAdapter  extends RecyclerView.Adapter<SavedMovieAdapter.ViewHolder>
{
    private List<MovieForRoom> dataMovies;
    private Context context;
    private MovieDeleted movieDeleted;
    public SavedMovieAdapter(List<MovieForRoom> dataMovies, Context context, MovieDeleted movieDeleted)
    {
        this.dataMovies=dataMovies;
        this.context=context;
        this.movieDeleted=movieDeleted;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_movies_card,parent,false);
        return new ViewHolder(view,movieDeleted);
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
        private MovieDeleted movieDeleted;
        public ViewHolder(@NonNull View itemView, MovieDeleted movieDeleted) {
            super(itemView);
            binding=ItemViewMoviesCardBinding.bind(itemView);
            this.movieDeleted=movieDeleted;
        }

        public void bind(MovieForRoom movie)
        {
            binding.movieTitle.setText(movie.getMovie_title());
            binding.ratingBar.setRating(movie.getRating());
            binding.movieImg.setVisibility(View.INVISIBLE);
            binding.removeBookmarkInDetails.setVisibility(View.VISIBLE);
            binding.textImgPlaceholder.setVisibility(View.VISIBLE);
            binding.removeBookmarkInDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RoomMoviesRepository moviesRepository = new RoomMoviesRepository(context);
                    moviesRepository.deleteTask(movie);
                    Toast.makeText(context, "Movie Removed", Toast.LENGTH_SHORT).show();
                    movieDeleted.onMovieDeleted();
                    notifyDataSetChanged();
                }
            });
        }

    }
    public interface MovieDeleted
    {
        void onMovieDeleted();
    }
}
