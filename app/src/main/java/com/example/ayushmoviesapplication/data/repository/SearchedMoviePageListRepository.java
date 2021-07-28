package com.example.ayushmoviesapplication.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.factory.SearchedMovieDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

public class SearchedMoviePageListRepository {
    private MovieInterface apiService;
    private String queryString;
    private SearchedMovieDataSourceFactory searchedMovieDataSourceFactory;
    private LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;
    public SearchedMoviePageListRepository(MovieInterface apiService, String queryString)
    {
        this.apiService=apiService;
        this.queryString=queryString;
    }
    public LiveData<PagedList<SearchedMovie>> fetchLiveSearchedMovieList(CompositeDisposable compositeDisposable)
    {
        searchedMovieDataSourceFactory=new SearchedMovieDataSourceFactory(apiService, compositeDisposable,queryString);
        PagedList.Config config= (new PagedList.Config.Builder())
                            .setEnablePlaceholders(false)
                            .setPageSize(Constants.POST_PER_PAGE)
                            .build();
        searchedMoviePagedList= new LivePagedListBuilder(searchedMovieDataSourceFactory,config).build();
        return searchedMoviePagedList;
    }

    public SearchedMovieDataSourceFactory getSearchedMovieDataSourceFactory()
    {
        return searchedMovieDataSourceFactory;
    }
}
