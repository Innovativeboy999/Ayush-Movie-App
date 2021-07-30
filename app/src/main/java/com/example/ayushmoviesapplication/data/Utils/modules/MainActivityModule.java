package com.example.ayushmoviesapplication.data.Utils.modules;

import android.app.AppOpsManager;

import com.example.ayushmoviesapplication.data.Utils.scope.MainActivityScope;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.NowPlayingFragment;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.PopularFragment;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.SavedMoviesFragment;
import com.example.ayushmoviesapplication.ui.Activities.Fragments.SearchFragment;
import com.example.ayushmoviesapplication.ui.Activities.MainActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {
    private final MainActivity mainActivity;
    public MainActivityModule(MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
    }
    @Provides
    @MainActivityScope
    public NowPlayingFragment getNowPlayingFragment()
    {
        return new NowPlayingFragment();
    }

    @Provides
    @MainActivityScope
    public PopularFragment getPopularFragment()
    {
        return new PopularFragment();
    }

    @Provides
    @MainActivityScope
    public SearchFragment getSearchFragment()
    {
        return new SearchFragment();
    }

    @Provides
    @MainActivityScope
    public SavedMoviesFragment getSavedMoviesFragment()
    {
        return new SavedMoviesFragment();
    }
}
