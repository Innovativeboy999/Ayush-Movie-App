package com.example.ayushmoviesapplication.data.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.repository.factory.NowPlayingDataSourceFactory;
import com.example.ayushmoviesapplication.data.repository.factory.PopularMovieDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

public class NowPlayingPageListRepository {

    private MovieInterface apiService;
    private NowPlayingDataSourceFactory nowPlayingDataSourceFactory;
    private LiveData<PagedList<Movie>> nowPlayingMoviePagedList;
    public NowPlayingPageListRepository(MovieInterface apiService)
    {
        this.apiService=apiService;
    }
    public LiveData<PagedList<Movie>> fetchLiveSearchedMovieList(CompositeDisposable compositeDisposable)
    {
        nowPlayingDataSourceFactory=new NowPlayingDataSourceFactory(apiService, compositeDisposable);
        PagedList.Config config= (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(Constants.POST_PER_PAGE)
                .build();

        nowPlayingMoviePagedList= new LivePagedListBuilder(nowPlayingDataSourceFactory,config).build();
        return nowPlayingMoviePagedList;
    }

}
