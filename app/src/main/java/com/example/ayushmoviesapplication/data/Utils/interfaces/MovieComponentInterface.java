package com.example.ayushmoviesapplication.data.Utils.interfaces;


import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.example.ayushmoviesapplication.data.Utils.modules.MovieClientModule;
import com.example.ayushmoviesapplication.data.Utils.scope.MovieComponentInterfaceScope;

import dagger.Component;

@MovieComponentInterfaceScope
@Component(modules = {MovieClientModule.class})
public interface MovieComponentInterface
{
   MovieInterface getApiService();

}
