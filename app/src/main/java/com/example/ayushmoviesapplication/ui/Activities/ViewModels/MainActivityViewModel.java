package com.example.ayushmoviesapplication.ui.Activities.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.NowPlayingPageListRepository;
import com.example.ayushmoviesapplication.data.repository.PopularMoviePageListRepository;
import com.example.ayushmoviesapplication.data.repository.SearchedMoviePageListRepository;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;
    public LiveData<PagedList<Movie>> popularMoviePagedList;
    public LiveData<PagedList<Movie>> nowPlayingPagedList;
    private PopularMoviePageListRepository popularMoviePageListRepository=new PopularMoviePageListRepository(MovieClient.getMovieInterface());
    private NowPlayingPageListRepository nowPlayingPageListRepository = new NowPlayingPageListRepository(MovieClient.getMovieInterface());
    public SearchedMoviePageListRepository searchedMoviePageListRepository =new SearchedMoviePageListRepository(MovieClient.getMovieInterface(), "");

    public MainActivityViewModel()
    {
        Log.i("1111111", "MainActivityViewModel: MainActivtiy ViewModel ");
    }


    public void getPopularMoviePagedList() {
        if(popularMoviePagedList==null)
        {
            popularMoviePagedList=popularMoviePageListRepository.fetchLiveSearchedMovieList(compositeDisposable);

        }
    }

    public void getSearchedMoviePagedList() {
        if(searchedMoviePagedList== null)
        {
            searchedMoviePagedList=searchedMoviePageListRepository.fetchLiveSearchedMovieList(compositeDisposable);
        }
    }


    public void getNowPlayingPagedList() {
        if(nowPlayingPagedList==null)
        {
            nowPlayingPagedList=nowPlayingPageListRepository.fetchLiveSearchedMovieList(compositeDisposable);
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
