package com.example.ayushmoviesapplication.data.repository.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.repository.dataSource.PopularMovieDataSource;

import io.reactivex.disposables.CompositeDisposable;

public class PopularMovieDataSourceFactory extends DataSource.Factory<Integer, Movie>{

        private MovieInterface apiService;
        private CompositeDisposable compositeDisposable;
        private MutableLiveData<PopularMovieDataSource> popularMovieLiveDataSource = new MutableLiveData<PopularMovieDataSource>();
        public PopularMovieDataSourceFactory(MovieInterface apiService, CompositeDisposable compositeDisposable)
        {
            this.apiService=apiService;
            this.compositeDisposable=compositeDisposable;
        }
        @NonNull
        @Override
        public DataSource<Integer, Movie> create() {
            PopularMovieDataSource dataSource=new PopularMovieDataSource(compositeDisposable,apiService);
            popularMovieLiveDataSource.postValue(dataSource);
            return dataSource;
        }

        public void refresh(String queryString)
        {
            popularMovieLiveDataSource.getValue().invalidate();
        }
}
