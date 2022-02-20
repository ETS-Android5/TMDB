package com.tmdb.data.retrofit;

import com.tmdb.data.response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("3/movie/popular")
    Call<ArticleResponse> getMovieArticles(
            @Query("api_key") String apiKey
    );
}
