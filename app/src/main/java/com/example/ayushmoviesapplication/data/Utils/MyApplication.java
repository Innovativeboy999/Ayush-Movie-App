package com.example.ayushmoviesapplication.data.Utils;

import android.app.Activity;
import android.app.Application;

import com.example.ayushmoviesapplication.data.Utils.interfaces.DaggerMovieComponentInterface;
import com.example.ayushmoviesapplication.data.Utils.interfaces.MovieComponentInterface;
import com.example.ayushmoviesapplication.data.Utils.modules.ContextModule;

public class MyApplication extends Application {
    private MovieComponentInterface globalComponent;

    public static MyApplication get(Activity activity)
    {
        return (MyApplication) activity.getApplication();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        globalComponent= DaggerMovieComponentInterface.builder()
                    .contextModule(new ContextModule(this))
                    .build();
    }

    public MovieComponentInterface getGlobalComponent()
    {
        return globalComponent;
    }
}
