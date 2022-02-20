/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.ui.details.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tmdb.common.view_model.BaseViewModel;
import com.tmdb.data.model.Article;
import com.tmdb.data.repository.ArticleRepository;
import com.tmdb.data.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * View model for Movie details activity
 */
public class DetailsViewModel extends BaseViewModel {

   private MovieRepository movieRepository;
   private List<Article> movieArticleList;
   private int movieID = -1;
   private MutableLiveData<String> backDropImageURLLiveData;
   private MutableLiveData<String> movieTitleLiveData;
   private MutableLiveData<String> movieOverViewLiveData;
   private MutableLiveData<Double> movieRatingLiveData;
   private MutableLiveData<Integer> votesLiveData;
   private MutableLiveData<String> movieReleaseDateLiveData;
   private MutableLiveData<List<Integer>> movieGenereLiveData;

   public DetailsViewModel(@NonNull Application application) {
      super(application);
      movieRepository = new ArticleRepository();
      movieArticleList = new ArrayList<>();
      backDropImageURLLiveData = new MutableLiveData<>();
      movieTitleLiveData = new MutableLiveData<>();
      movieOverViewLiveData = new MutableLiveData<>();
      movieRatingLiveData = new MutableLiveData<>();
      votesLiveData = new MutableLiveData<>();
      movieReleaseDateLiveData = new MutableLiveData<>();
      movieGenereLiveData = new MutableLiveData<>();
      subscribeData();
   }

   /**
    * Subscribe to observables
    */
   private void subscribeData() {
      addDisposable(movieRepository.getMovieArticles().distinctUntilChanged().subscribe(movieList -> {
         movieArticleList.addAll(movieList);
         if (movieID != -1) {
            updateMovieDetailsForId(movieID);
         }
      }));
   }

   /**
    * Update respective live data
    * @param id
    */
   private void updateMovieDetailsForId(int id) {
      movieID = id;
      if (!movieArticleList.isEmpty()) {
         Article movieArticle = movieArticleList.get(id);
         backDropImageURLLiveData.postValue(movieArticle.getBackdrop_path());
         movieTitleLiveData.postValue(movieArticle.getTitle());
         movieOverViewLiveData.postValue(movieArticle.getOverview());
         movieRatingLiveData.postValue(movieArticle.getVote_average());
         votesLiveData.postValue(movieArticle.getVote_count());
         movieReleaseDateLiveData.postValue(movieArticle.getRelease_date());
         movieGenereLiveData.postValue(movieArticle.getGenre_ids());
      }
   }

   /**
    * Get url to movie back drop image
    * @return
    */
   public LiveData<String> getBackDropImageURLLiveData() {
      return backDropImageURLLiveData;
   }

   /**
    * Get movie tille
    * @return
    */
   public LiveData<String> getMovieTitleLiveData() {
      return movieTitleLiveData;
   }

   /**
    * Get movie description
    * @return
    */
   public LiveData<String> getMovieOverViewLiveData() {
      return movieOverViewLiveData;
   }

   /**
    * Get movie average movie rating
    * @return
    */
   public LiveData<Double> getMovieRatingLiveData() {
      return movieRatingLiveData;
   }

   /**
    * Get votes Count
    * @return
    */
   public LiveData<Integer> getVotesLiveData() {
      return votesLiveData;
   }

   /**
    * Get movie release date
    * @return
    */
   public LiveData<String> getMovieReleaseDateLiveData() {
      return movieReleaseDateLiveData;
   }

   /**
    * Get list for movie genere
    * @return
    */
   public LiveData<List<Integer>> getMovieGenereLiveData() {
      return movieGenereLiveData;
   }

   /**
    * Set movie id to fetch movie details for the movie
    * @param id
    */
   public void setMovieId(int id) {
      updateMovieDetailsForId(id);
   }

   protected void clearReferences() {
      movieArticleList = null;
      movieRepository = null;
      backDropImageURLLiveData = null;
      movieTitleLiveData = null;
      movieOverViewLiveData = null;
      movieRatingLiveData = null;
      votesLiveData = null;
      movieReleaseDateLiveData = null;
      movieGenereLiveData = null;
   }
}
