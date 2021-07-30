package com.example.ayushmoviesapplication.ui.Activities.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.models.MovieDetailsResponse.MovieDetailsResponse;
import com.example.ayushmoviesapplication.data.repository.MovieDetailRepository;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailViewModel extends ViewModel {
    private MovieDetailRepository repository =new MovieDetailRepository(MovieClient.getMovieInterface());
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<MovieDetailsResponse> movieDetailsResponseLiveData;
    public  MovieDetailViewModel()
    {
        Log.i("11111", "MovieDetailViewModel: object created ");
    }
    public void fetchMovieDetails(int movie_id)
    {
        movieDetailsResponseLiveData=repository.getMovieDetailLiveData(compositeDisposable,movie_id);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
