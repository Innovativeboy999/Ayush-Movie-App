package com.example.ayushmoviesapplication.data.models.SearchedMovieList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchMovieResponse {


        @SerializedName("page")
        @Expose
        private int page;
        @SerializedName("results")
        @Expose
        private List<SearchedMovie> searchedMovies = null;
        @SerializedName("total_pages")
        @Expose
        private int totalPages;
        @SerializedName("total_results")
        @Expose
        private int totalResults;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<SearchedMovie> getResults() {
            return searchedMovies;
        }

        public void setResults(List<SearchedMovie> searchedMovies) {
            this.searchedMovies = searchedMovies;
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
