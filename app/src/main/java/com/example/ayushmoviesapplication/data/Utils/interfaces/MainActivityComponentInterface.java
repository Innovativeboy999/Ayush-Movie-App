package com.example.ayushmoviesapplication.data.Utils.interfaces;

import com.example.ayushmoviesapplication.data.Utils.modules.MainActivityModule;
import com.example.ayushmoviesapplication.data.Utils.scope.MainActivityScope;
import com.example.ayushmoviesapplication.ui.Activities.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class,dependencies = MovieComponentInterface.class)
@MainActivityScope
public interface MainActivityComponentInterface {
    void injectMainActivity(MainActivity mainActivity);
}
