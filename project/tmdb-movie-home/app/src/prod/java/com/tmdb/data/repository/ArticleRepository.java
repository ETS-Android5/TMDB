package com.tmdb.data.repository;

import android.util.Log;

import com.tmdb.common.constants.AppConstant;
import com.tmdb.data.model.Article;
import com.tmdb.data.response.ArticleResponse;
import com.tmdb.data.retrofit.ApiRequest;
import com.tmdb.data.retrofit.RetrofitRequest;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepository implements MovieRepository {
   private static final String TAG = ArticleRepository.class.getSimpleName();
   private ApiRequest apiRequest;
   private ArticleResponse articleResponse;
   private BehaviorSubject movieListSubject;

   public ArticleRepository() {
      apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
      movieListSubject = BehaviorSubject.create();
      fetchMovieArticles(AppConstant.API_KEY);
   }

   @Override
   public Observable<List<Article>> getMovieArticles() {
      return movieListSubject;
   }

   public void fetchMovieArticles(String key) {
      apiRequest.getMovieArticles(key).enqueue(new Callback<ArticleResponse>() {

         @Override
         public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
            Log.e(TAG, "onResponse response:: " + response);

            if (response.body() != null) {
               articleResponse = response.body();
               movieListSubject.onNext(articleResponse.getArticles());
               Log.e(TAG, "articles total result:: " + response.body().getTotalResults());
               Log.e(TAG, "articles size:: " + response.body().getArticles().size());
               Log.e(TAG, "articles title pos 0:: " + response.body().getArticles().get(0).getTitle());
            }
         }

         @Override
         public void onFailure(Call<ArticleResponse> call, Throwable t) {
            Log.e(TAG, "fetching movie list failed");
         }
      });
   }
}
