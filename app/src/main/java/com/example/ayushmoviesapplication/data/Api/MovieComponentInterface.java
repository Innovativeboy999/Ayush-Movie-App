package com.example.ayushmoviesapplication.data.Api;


import com.example.ayushmoviesapplication.data.Utils.MovieClientModule;
import com.example.ayushmoviesapplication.data.Utils.MovieComponentInterfaceScope;

import dagger.Component;

@MovieComponentInterfaceScope
@Component(modules = {MovieClientModule.class})
public interface MovieComponentInterface
{
   MovieInterface getApiService();

}
