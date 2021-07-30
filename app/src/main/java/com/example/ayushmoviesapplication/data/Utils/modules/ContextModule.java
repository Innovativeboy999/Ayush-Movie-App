package com.example.ayushmoviesapplication.data.Utils.modules;


import android.content.Context;
import com.example.ayushmoviesapplication.data.Utils.scope.MovieComponentInterfaceScope;
import javax.inject.Named;
import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    Context context;

    public ContextModule(Context context){
        this.context = context;
    }

    @Named("activity_context")
    @MovieComponentInterfaceScope
    @Provides
    public Context context(){ return context.getApplicationContext(); }
}
