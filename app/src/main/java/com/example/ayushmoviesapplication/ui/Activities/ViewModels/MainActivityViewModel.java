package com.example.ayushmoviesapplication.ui.Activities.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.SearchedMoviePageListRepository;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;

    public MainActivityViewModel(SearchedMoviePageListRepository repository)
    {
        Log.i("1111111", "MainActivityViewModel: MainActivtiy ViewModel ");
        searchedMoviePagedList=repository.fetchLiveSearchedMovieList(compositeDisposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
