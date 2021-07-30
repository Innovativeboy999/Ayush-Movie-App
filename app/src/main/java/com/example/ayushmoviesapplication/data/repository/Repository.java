package com.example.ayushmoviesapplication.data.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.repository.factory.NowPlayingDataSourceFactory;
import com.example.ayushmoviesapplication.data.repository.factory.PopularMovieDataSourceFactory;
import com.example.ayushmoviesapplication.data.repository.factory.SearchedMovieDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

public class Repository {

    private MovieInterface apiService;
    private NowPlayingDataSourceFactory nowPlayingDataSourceFactory;
    private LiveData<PagedList<Movie>> nowPlayingMoviePagedList;
    private PopularMovieDataSourceFactory popularMovieDataSourceFactory;
    private LiveData<PagedList<Movie>> popularMoviePagedList;
    private SearchedMovieDataSourceFactory searchedMovieDataSourceFactory;
    private LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;

    public Repository(MovieInterface apiService)
    {
        this.apiService=apiService;
    }
    public LiveData<PagedList<Movie>> fetchLiveNowPlayingMovieList(CompositeDisposable compositeDisposable)
    {
        nowPlayingDataSourceFactory=new NowPlayingDataSourceFactory(apiService, compositeDisposable);
        PagedList.Config config= (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.POST_PER_PAGE)
                .build();

        nowPlayingMoviePagedList= new LivePagedListBuilder(nowPlayingDataSourceFactory,config).build();
        return nowPlayingMoviePagedList;
    }

    public LiveData<PagedList<Movie>> fetchLivePopularMovieList(CompositeDisposable compositeDisposable)
    {
        popularMovieDataSourceFactory=new PopularMovieDataSourceFactory(apiService, compositeDisposable);
        PagedList.Config config= (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.POST_PER_PAGE)
                .build();
        popularMoviePagedList= new LivePagedListBuilder(popularMovieDataSourceFactory,config).build();
        return popularMoviePagedList;
    }
    public LiveData<PagedList<SearchedMovie>> fetchLiveSearchedMovieList(CompositeDisposable compositeDisposable, String queryString)
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