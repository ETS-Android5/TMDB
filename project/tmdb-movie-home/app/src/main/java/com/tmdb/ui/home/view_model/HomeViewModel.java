/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */

package com.tmdb.ui.home.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tmdb.common.view_model.BaseViewModel;
import com.tmdb.data.model.Article;
import com.tmdb.data.repository.ArticleRepository;
import com.tmdb.data.repository.MovieRepository;
import com.tmdb.ui.home.entities.HomeUIEntity;

import java.util.ArrayList;
import java.util.List;

import static com.tmdb.common.constants.AppConstant.API_KEY;

public class HomeViewModel extends BaseViewModel {

   private MovieRepository movieRepository;
   private MutableLiveData<List<HomeUIEntity>> entityLiveData;

   public HomeViewModel(@NonNull Application application) {
      super(application);

      movieRepository = new ArticleRepository();
      entityLiveData = new MutableLiveData<>();
      subscribeData();
   }

   private void subscribeData() {
      addDisposable(movieRepository.getMovieArticles().distinctUntilChanged().subscribe(movieList -> {
         List<HomeUIEntity> homeUIEntities = new ArrayList<>();
         for (Article article : movieList) {
            homeUIEntities.add(new HomeUIEntity(article.getId(), article.getTitle(), article.getOverview(), article.getPoster_path(), article.getVote_count(), article.getVote_average()));
         }
         entityLiveData.postValue(homeUIEntities);
      }));
   }

      public LiveData<List<HomeUIEntity>> getEntityLiveData () {
         return entityLiveData;
      }

      protected void clearReferences () {
         movieRepository = null;
         entityLiveData = null;
      }
   }
