package com.example.ayushmoviesapplication.data.Utils;

import com.example.ayushmoviesapplication.data.Api.MovieComponentInterface;
import com.example.ayushmoviesapplication.data.Api.MovieInterface;

import dagger.Component;

@Component(dependencies = MovieComponentInterface.class)
@MainActivityScope
public interface MainActivityComponentInterface {
    MovieInterface getApiService();
}
