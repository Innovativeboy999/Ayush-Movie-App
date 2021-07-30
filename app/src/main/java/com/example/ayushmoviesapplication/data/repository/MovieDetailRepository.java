package com.example.ayushmoviesapplication.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.MovieDetailsResponse.MovieDetailsResponse;
import com.example.ayushmoviesapplication.data.repository.dataSource.MovieDetailDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class MovieDetailRepository {
    private MovieInterface apiService;
    private MovieDetailDataSource movieDetailDataSource;
    public MovieDetailRepository(MovieInterface apiService)
    {
        this.apiService=apiService;
    }
    public LiveData<MovieDetailsResponse> getMovieDetailLiveData(CompositeDisposable compositeDisposable, int movie_id)
    {
        movieDetailDataSource=new MovieDetailDataSource(compositeDisposable,apiService);
        movieDetailDataSource.fetchMovieDetails(movie_id);
        Log.i("111111", "getMovieDetailLiveData: "+(movieDetailDataSource.movieDetailsResponseLiveData==null));
        return movieDetailDataSource.movieDetailsResponseLiveData;
    }
}
