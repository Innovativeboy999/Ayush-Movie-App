package com.example.ayushmoviesapplication.data.repository.dataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchMovieResponse;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SearchedMovieDataSource extends PageKeyedDataSource<Integer, SearchedMovie>
    {
        private CompositeDisposable compositeDisposable;
        private MovieInterface apiService;
        private String queryString;
        int count =1;
        public SearchedMovieDataSource(CompositeDisposable compositeDisposable, MovieInterface apiService,String queryString)
        {
            this.compositeDisposable=compositeDisposable;
            this.apiService=apiService;
            this.queryString=queryString;
        }

        @Override
        public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, SearchedMovie> callback) {
            compositeDisposable.add( apiService.searchMovie(Constants.API_KEY,queryString,Integer.toString(count))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<SearchMovieResponse>() {
                        @Override
                        public void accept(SearchMovieResponse searchMovieResponse) throws Exception {
                            callback.onResult(searchMovieResponse.getSearchedMovies(),null,count+1);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("111111", "loadInitial: inside Search Movie data source  load initial"+throwable.getMessage());
                        }
                    }));
        }

        @Override
        public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, SearchedMovie> callback) {

        }

        @Override
        public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, SearchedMovie> callback) {
            compositeDisposable.add( apiService.searchMovie(Constants.API_KEY,queryString,Integer.toString(params.key))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<SearchMovieResponse>() {
                        @Override
                        public void accept(SearchMovieResponse searchMovieResponse) throws Exception {
                            callback.onResult(searchMovieResponse.getSearchedMovies(),params.key+1);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.e("111111", "loadAfter: inside icon data source  load initial"+throwable.getMessage());
                        }
                    }));
        }
    }
