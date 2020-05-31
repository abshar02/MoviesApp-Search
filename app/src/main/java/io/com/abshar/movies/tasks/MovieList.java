package io.com.abshar.movies.tasks;

import java.util.ArrayList;

import io.com.abshar.movies.models.MovieListModel;

public interface MovieList {
    interface View {
        void setMovieList(ArrayList<MovieListModel.Movie> movieList);

        void updateUi(Boolean isLoading);

        void runLayoutAnimation();

        void onSuccess();

        void onFailed(String message);
    }

    interface Presenter {
        void getMoviesList(String title);

        void getMoreMovies();
    }
}
