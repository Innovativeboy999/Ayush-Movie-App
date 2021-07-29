package com.example.ayushmoviesapplication.data.models.NowPlayingResponse;

import com.example.ayushmoviesapplication.data.models.PopularMoviesList.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponse {

        @SerializedName("dates")
        @Expose
        private Dates dates;
        @SerializedName("page")
        @Expose
        private int page;
        @SerializedName("results")
        @Expose
        private List<Movie> movies = null;
        @SerializedName("total_pages")
        @Expose
        private int totalPages;
        @SerializedName("total_results")
        @Expose
        private int totalResults;

        public Dates getDates() {
            return dates;
        }

        public void setDates(Dates dates) {
            this.dates = dates;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<Movie> getMovies() {
            return movies;
        }

        public void setResults(List<Movie> movies) {
            this.movies = movies;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(int totalResults) {
            this.totalResults = totalResults;
        }


}
