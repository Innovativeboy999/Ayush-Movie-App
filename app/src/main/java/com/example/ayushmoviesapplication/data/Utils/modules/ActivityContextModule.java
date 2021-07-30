package com.example.ayushmoviesapplication.data.Utils.modules;

import android.app.Activity;
import android.content.Context;
import com.example.ayushmoviesapplication.data.Utils.scope.MovieComponentInterfaceScope;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityContextModule {

    private final Context context;

    ActivityContextModule(Activity context){
        this.context = context;
    }

    @Named("activity_context")
    @MovieComponentInterfaceScope
    @Provides
    public Context context(){ return context; }
}