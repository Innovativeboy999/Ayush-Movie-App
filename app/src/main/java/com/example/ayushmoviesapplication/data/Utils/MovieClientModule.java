package com.example.ayushmoviesapplication.data.Utils;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ContextModule.class)
public class MovieClientModule {
    @MovieComponentInterfaceScope
    @Provides
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }
    @MovieComponentInterfaceScope
    @Provides
    public HttpLoggingInterceptor getLoggingInterceptor()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @MovieComponentInterfaceScope
    @Provides
    public OkHttpClient getRequestHeader(HttpLoggingInterceptor loggingInterceptor) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).build();

        return okHttpClient;
    }

    @MovieComponentInterfaceScope
    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }

    @MovieComponentInterfaceScope
    @Provides
    public MovieInterface getApiInterfaceService(Retrofit retrofit) {
        return retrofit.create(MovieInterface.class);
    }
}
