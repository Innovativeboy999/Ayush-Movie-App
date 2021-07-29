package com.example.ayushmoviesapplication.data.repository.dataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.NowPlayingResponse.NowPlayingResponse;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NowPlayingDataSource extends PageKeyedDataSource<Integer, Movie> {
    private CompositeDisposable compositeDisposable;
    private MovieInterface apiService;
    int count =1;

    public NowPlayingDataSource(CompositeDisposable compositeDisposable, MovieInterface apiService)
    {
        this.compositeDisposable=compositeDisposable;
        this.apiService=apiService;
    }
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Movie> callback) {
        compositeDisposable.add(apiService.getNowPlaying(Constants.API_KEY, "en-US",Integer.toString(count))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NowPlayingResponse>() {
                    @Override
                    public void accept(NowPlayingResponse nowPlayingResponse) throws Exception {
                        callback.onResult(nowPlayingResponse.getMovies(),null,count+1);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("111111", "loadInitial: inside Now Playing Movie data source  load initial"+throwable.getMessage());

                    }
                }));
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Movie> callback) {
        compositeDisposable.add(apiService.getNowPlaying(Constants.API_KEY, "en-US",Integer.toString(params.key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NowPlayingResponse>() {
                    @Override
                    public void accept(NowPlayingResponse nowPlayingResponse) throws Exception {
                        callback.onResult(nowPlayingResponse.getMovies(),params.key+1);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("111111", "loadAfter: inside Now Playing Movie data source  load after"+throwable.getMessage());

                    }
                }));
    }
}
