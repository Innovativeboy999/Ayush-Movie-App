package com.example.ayushmoviesapplication.ui.Activities.ViewModels;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.ayushmoviesapplication.data.Api.MovieClient;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchedMovie;
import com.example.ayushmoviesapplication.data.models.roommodel.MovieForRoom;
import com.example.ayushmoviesapplication.data.repository.Repository;
import com.example.ayushmoviesapplication.data.repository.RoomRepository.RoomMoviesRepository;
import com.example.ayushmoviesapplication.ui.Activities.MainActivity;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class MainActivityViewModel extends ViewModel {
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    public LiveData<PagedList<SearchedMovie>> searchedMoviePagedList;
    public LiveData<PagedList<Movie>> popularMoviePagedList;
    public LiveData<PagedList<Movie>> nowPlayingPagedList;
    public MutableLiveData<List<MovieForRoom>> savedMoviesMutableLivedata=new MutableLiveData<>();
    public LiveData<List<MovieForRoom>> savedMoviesLiveData=savedMoviesMutableLivedata;
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

    public void getListOfSavedMovies(Context context, FragmentActivity activity)
    {
        RoomMoviesRepository moviesRepository = new RoomMoviesRepository(context);

        moviesRepository.getTasks().observe(activity, new Observer<List<MovieForRoom>>() {
            @Override
            public void onChanged(@Nullable List<MovieForRoom> movies) {
                    savedMoviesMutableLivedata.postValue(movies);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
