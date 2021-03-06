package io.com.abshar.movies.presenters;

import android.support.annotation.NonNull;

import java.util.HashMap;

import io.com.abshar.movies.api.MoviesApiService;
import io.com.abshar.movies.models.MovieModel;
import io.com.abshar.movies.tasks.Movie;
import io.com.abshar.movies.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviePresenter implements Movie.Presenter {

    private Movie.View view;
    private HashMap<String, String> params;
    private MoviesApiService moviesApiService;

    public MoviePresenter(Movie.View view) {
        this.view = view;
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moviesApiService = retrofit.create(MoviesApiService.class);
    }

    @Override
    public void getMovie(String imdbID) {
        initParams(imdbID);
        Call<MovieModel> call = moviesApiService.getMovie(params);
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(@NonNull Call<MovieModel> call, @NonNull Response<MovieModel> response) {
                final MovieModel movie = response.body();
                if (response.isSuccessful() && movie != null) {
                    view.showMovie(movie);
                } else {
                    view.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieModel> call, @NonNull Throwable t) {
                view.onFailed(t.getMessage());
            }
        });
    }

    private void initParams(String imdbID) {
        params = new HashMap<>();
        params.put(Constants.getParamId(), imdbID);
        params.put(Constants.getParamPlot(), "full");
        params.put(Constants.getParamApiKey(), Constants.getApiKey());
    }
}
