package com.example.ayushmoviesapplication.data.repository.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.dataSource.SearchedMovieDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class SearchedMovieDataSourceFactory extends DataSource.Factory<Integer, SearchedMovie> {

    private MovieInterface apiService;
    private CompositeDisposable compositeDisposable;
    private String queryString;
    private MutableLiveData<SearchedMovieDataSource> searchedMovieLiveDataSource = new MutableLiveData<SearchedMovieDataSource>();
    public SearchedMovieDataSourceFactory(MovieInterface apiService, CompositeDisposable compositeDisposable,String queryString)
    {
        this.apiService=apiService;
        this.compositeDisposable=compositeDisposable;
        this.queryString=queryString;
    }
    @NonNull
    @Override
    public DataSource<Integer, SearchedMovie> create() {
        SearchedMovieDataSource dataSource=new SearchedMovieDataSource(compositeDisposable,apiService,queryString);
        searchedMovieLiveDataSource.postValue(dataSource);
        return dataSource;
    }

    public void refresh(String queryString)
    {
        this.queryString=queryString;
        searchedMovieLiveDataSource.getValue().invalidate();
    }

}
