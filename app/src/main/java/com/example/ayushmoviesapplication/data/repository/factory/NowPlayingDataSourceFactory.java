package com.example.ayushmoviesapplication.data.repository.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.repository.dataSource.NowPlayingDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class NowPlayingDataSourceFactory extends DataSource.Factory<Integer, Movie>{

    private MovieInterface apiService;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<NowPlayingDataSource> nowPlayingLiveDataSource = new MutableLiveData<NowPlayingDataSource>();
    public NowPlayingDataSourceFactory(MovieInterface apiService, CompositeDisposable compositeDisposable)
    {
        this.apiService=apiService;
        this.compositeDisposable=compositeDisposable;
    }
    @NonNull
    @Override
    public DataSource<Integer, Movie> create() {
        NowPlayingDataSource dataSource=new NowPlayingDataSource(compositeDisposable,apiService);
        nowPlayingLiveDataSource.postValue(dataSource);
        return dataSource;    }
}
