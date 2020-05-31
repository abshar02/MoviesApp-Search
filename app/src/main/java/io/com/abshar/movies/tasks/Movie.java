package io.com.abshar.movies.tasks;

import io.com.abshar.movies.models.MovieModel;

public interface Movie {
    interface View {
        void showMovie(MovieModel movie);

        void onFailed(String message);
    }

    interface Presenter {
        void getMovie(String imdbID);
    }
}
