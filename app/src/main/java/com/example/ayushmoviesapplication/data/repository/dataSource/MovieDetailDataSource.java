package com.example.ayushmoviesapplication.data.repository.dataSource;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.MovieDetailsResponse.MovieDetailsResponse;

import java.nio.file.ClosedFileSystemException;

import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailDataSource
{
    private CompositeDisposable compositeDisposable;
    private MovieInterface apiService;
    private MutableLiveData<MovieDetailsResponse> movieDetailResponseMutableLiveData = new MutableLiveData<>();
    public LiveData<MovieDetailsResponse> movieDetailsResponseLiveData;
    public MovieDetailDataSource(CompositeDisposable compositeDisposable, MovieInterface apiService)
    {
        this.compositeDisposable=compositeDisposable;
        this.apiService=apiService;
    }
    public void fetchMovieDetails(int id)
    {
        Log.i("111111", "fetchMovieDetails: data source method called "+(compositeDisposable==null)+" "+(apiService==null));
        compositeDisposable.add(apiService.getMovieDetailsById(id, Constants.API_KEY)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<MovieDetailsResponse>() {
                                    @Override
                                    public void accept(MovieDetailsResponse movieDetailsResponse) throws Exception {
                                        Log.i("11111", "accept: success"+movieDetailsResponse.getTitle());
                                        movieDetailResponseMutableLiveData.postValue(movieDetailsResponse);
                                        movieDetailsResponseLiveData=movieDetailResponseMutableLiveData;

                                    }
                                }, new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Exception {
                                        Log.i("1111", "accept: erro"+throwable.getMessage());
                                    }
                                }));

    }
}
