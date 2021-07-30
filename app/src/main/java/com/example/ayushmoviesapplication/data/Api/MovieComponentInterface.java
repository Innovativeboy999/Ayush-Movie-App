package com.example.ayushmoviesapplication.data.Api;


import com.example.ayushmoviesapplication.data.Utils.MovieClientModule;

import dagger.Component;

@Component(modules = {MovieClientModule.class})
public interface MovieComponentInterface
{
   MovieInterface getApiService();

}
