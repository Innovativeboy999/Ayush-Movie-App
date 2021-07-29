package com.example.ayushmoviesapplication.data.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.Constants;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.repository.factory.PopularMovieDataSourceFactory;

import io.reactivex.disposables.CompositeDisposable;

public class PopularMoviePageListRepository {

       private MovieInterface apiService;
        private PopularMovieDataSourceFactory popularMovieDataSourceFactory;
        private LiveData<PagedList<Movie>> popularMoviePagedList;
        public PopularMoviePageListRepository(MovieInterface apiService)
        {
            this.apiService=apiService;
        }
        public LiveData<PagedList<Movie>> fetchLiveSearchedMovieList(CompositeDisposable compositeDisposable)
        {
            popularMovieDataSourceFactory=new PopularMovieDataSourceFactory(apiService, compositeDisposable);
            PagedList.Config config= (new PagedList.Config.Builder())
                    .setEnablePlaceholders(false)
                    .setPageSize(Constants.POST_PER_PAGE)
                    .build();
            popularMoviePagedList= new LivePagedListBuilder(popularMovieDataSourceFactory,config).build();
            return popularMoviePagedList;
        }

        public PopularMovieDataSourceFactory getSearchedMovieDataSourceFactory()
        {
            return popularMovieDataSourceFactory;
        }
}
