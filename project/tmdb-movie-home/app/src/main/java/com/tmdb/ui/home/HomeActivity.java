package com.tmdb.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tmdb.common.activity.BaseActivity;
import com.tmdb.movieshome.R;
import com.tmdb.ui.details.DetailsActivity;
import com.tmdb.ui.home.adapter.MovieArticleAdapter;
import com.tmdb.ui.home.entities.HomeUIEntity;
import com.tmdb.ui.home.view_model.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

   private static final String TAG = HomeActivity.class.getSimpleName();
   private LinearLayoutManager layoutManager;
   private MovieArticleAdapter movieArticleAdapter;
   private List<HomeUIEntity> homeUIEntities = new ArrayList<>();
   private HomeViewModel homeViewModel;
   @BindView(R.id.recycler_view) RecyclerView homeRecyclerView;
   @BindView(R.id.progress_circular_movie_article) ProgressBar progress_circular_movie_article;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      init();
   }

   /**
    * initialization of views and others
    *
    * @param @null
    */
   private void init() {
      layoutManager = new LinearLayoutManager(HomeActivity.this);
      homeRecyclerView.setLayoutManager(layoutManager);
      homeRecyclerView.setHasFixedSize(true);

      // adapter
      movieArticleAdapter = new MovieArticleAdapter(HomeActivity.this, homeUIEntities);
      movieArticleAdapter.setOnItemClickListener((int id) -> {
         Intent intent = new Intent(this, DetailsActivity.class);
         intent.putExtra("article_id", id);
         startActivity(intent);
      });
      homeRecyclerView.setAdapter(movieArticleAdapter);

      // View Model
      homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

      subscribeLiveData();
   }

   /**
    * get movies articles from news api
    *
    * @param @null
    */
   private void subscribeLiveData() {
      homeViewModel.getEntityLiveData().observe(this, uiEntities -> {
         if (uiEntities != null) {
            progress_circular_movie_article.setVisibility(View.GONE);
            homeUIEntities.addAll(uiEntities);
            movieArticleAdapter.notifyDataSetChanged();
         }
      });
   }

   @Override
   protected int getLayout() {
      return R.layout.activity_main;
   }
}
