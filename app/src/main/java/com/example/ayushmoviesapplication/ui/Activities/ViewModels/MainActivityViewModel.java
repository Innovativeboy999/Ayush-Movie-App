package com.example.ayushmoviesapplication.ui.Activities.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.Repository;
import com.example.ayushmoviesapplication.ui.Activities.MainActivity;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;
    public LiveData<PagedList<Movie>> popularMoviePagedList;
    public LiveData<PagedList<Movie>> nowPlayingPagedList;
//    public Repository repository=new Repository(MovieClient.getMovieInterface());
    public Repository repository=new Repository(MainActivity.getGetApiService);

    public MainActivityViewModel()
    {
    }


    public void getPopularMoviePagedList() {
        if(popularMoviePagedList==null)
        {
            popularMoviePagedList=repository.fetchLivePopularMovieList(compositeDisposable);

        }
    }

    public void getSearchedMoviePagedList() {
        if(searchedMoviePagedList== null)
        {
            searchedMoviePagedList=repository.fetchLiveSearchedMovieList(compositeDisposable,"");
        }
    }


    public void getNowPlayingPagedList() {
        if(nowPlayingPagedList==null)
        {
            nowPlayingPagedList=repository.fetchLiveNowPlayingMovieList(compositeDisposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
