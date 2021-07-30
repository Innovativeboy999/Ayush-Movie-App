package com.example.ayushmoviesapplication.data.Utils;

import com.example.ayushmoviesapplication.data.Api.MovieInterface;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class UtilsModule {
    @Provides
    @Singleton
    HttpLoggingInterceptor getLoggingInterceptor()
    {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    @Provides
    @Singleton
    OkHttpClient getRequestHeader(HttpLoggingInterceptor loggingInterceptor) {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS).build();

        return okHttpClient;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit( OkHttpClient okHttpClient) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Provides
    @Singleton
    MovieInterface getApiInterfaceService(Retrofit retrofit) {
        return retrofit.create(MovieInterface.class);
    }




}
