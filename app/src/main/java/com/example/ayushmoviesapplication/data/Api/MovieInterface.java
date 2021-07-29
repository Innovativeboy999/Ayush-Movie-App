package com.example.ayushmoviesapplication.data.Api;

import com.example.ayushmoviesapplication.data.models.MovieDetailsResponse.MovieDetailsResponse;
import com.example.ayushmoviesapplication.data.models.NowPlayingResponse.NowPlayingResponse;
import com.example.ayushmoviesapplication.data.models.PopularMoviesList.PopularMovieList;
import com.example.ayushmoviesapplication.data.models.SearchedMovieList.SearchMovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    //https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher
    @GET("3/search/movie")
    Single<SearchMovieResponse> searchMovie(
            @Query("api_key") String token,
            @Query("query") String query ,
            @Query("page") String page);

    //https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    @GET("3/movie/popular")
    Single<PopularMovieList> getTrendingMovies(
            @Query("api_key") String token,
            @Query("language") String language ,
            @Query("page") String page
    );

    //https://api.themoviedb.org/3/movie/now_playing?api_key=<<api_key>>&language=en-US&page=1
    @GET("3/movie/now_playing")
    Single<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String token,
            @Query("language") String language ,
            @Query("page") String page
    );

    //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
    @GET("3/movie/{movie_id}")
    Single<MovieDetailsResponse> getMovieDetailsById(
            @Path("movie_id") int id,
            @Query("api_key") String token,
            @Query("language") String language
            );
}
