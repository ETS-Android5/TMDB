/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.ui.details;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.tmdb.common.activity.BaseActivity;
import com.tmdb.common.constants.AppConstant;
import com.tmdb.data.model.Article;
import com.tmdb.movieshome.R;
import com.tmdb.ui.details.view_model.DetailsViewModel;
import com.tmdb.ui.home.HomeActivity;

import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * Activity to show movie details/article
 */
public class DetailsActivity extends BaseActivity {

   private static final String TAG = DetailsActivity.class.getSimpleName();
   private DetailsViewModel detailsViewModel;
   @BindView(R.id.iv_activity_movie_details) AppCompatImageView backDropImage;
   @BindView(R.id.progress_bar) ProgressBar progressBar;
   @BindView(R.id.detail_description) TextView descriptionTV;
   @BindView(R.id.detail_rating) AppCompatTextView ratingTV;
   @BindView(R.id.detail_votes) AppCompatTextView voteCountTV;
   @BindView(R.id.release_date) AppCompatTextView releaseDateTV;
   @BindView(R.id.genres_chip_group) ChipGroup genreGroup;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      Intent intent = getIntent();
      int movieId  = intent.getIntExtra("article_id", 0);
      setUpToolBar();
      init(movieId);
   }

   private void setUpToolBar() {
      getSupportActionBar().setDisplayShowTitleEnabled(true);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
   }

   /**
    * initialization of views and subscribe to livedata
    */
   private void init(int id) {

      // View Model
      detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
      detailsViewModel.setMovieId(id);
      subscribeLiveData();
   }

   /**
    * subcribe to livedata
    */
   private void subscribeLiveData() {
      detailsViewModel.getBackDropImageURLLiveData().observe(this, this::updateBackDropImage);
      detailsViewModel.getMovieTitleLiveData().observe(this, title -> setTitle(title));
      detailsViewModel.getMovieOverViewLiveData().observe(this, overView -> descriptionTV.setText(overView));
      detailsViewModel.getMovieRatingLiveData().observe(this, ratings -> ratingTV.setText("" + ratings));
      detailsViewModel.getVotesLiveData().observe(this, votes -> voteCountTV.setText("" + votes));
      detailsViewModel.getMovieReleaseDateLiveData().observe(this, releaseDate -> releaseDateTV.setText(releaseDate));
      detailsViewModel.getMovieGenereLiveData().observe(this, this::addGenere);
   }

   /**
    *
    * @param url
    */
   private void updateBackDropImage(String url) {
      Glide.with(DetailsActivity.this).load(url).into(backDropImage);
      progressBar.setVisibility(View.GONE);
   }

   /**
    * Add chips for all generes types
    * @param generes
    */
   private void addGenere(List<Integer> generes) {
      for (Integer id : generes) {
         String genereString = getResources().getString(AppConstant.GENERE_ID_MAPPER.get(id));
         Chip chip = new Chip(this);
         chip.setText(genereString);
         genreGroup.addView(chip);
      }

   }

   @Override
   protected int getLayout() {
      return R.layout.activity_movie_details;
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {
      finish();
      return super.onOptionsItemSelected(item);
   }
}
