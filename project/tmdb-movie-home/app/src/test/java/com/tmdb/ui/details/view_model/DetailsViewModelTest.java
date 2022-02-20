/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.ui.details.view_model;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.tmdb.data.model.Article;
import com.tmdb.data.repository.MovieRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.subjects.BehaviorSubject;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class DetailsViewModelTest {

   @Rule public TestRule rule = new InstantTaskExecutorRule();
   @Mock MovieRepository movieRepository;
   @Mock Application application;
   private BehaviorSubject<List<Article>> movieArticlesBehaviorSubject;
   private DetailsViewModel detailsViewModel;
   private String[] backDropPath = { "/6ELCZlTA5lGUops70hKdB83WJxH.jpg", "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg", "/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg", "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
      "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg" };
   private String[] posterPath = { "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg", "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg", "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
      "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg" };
   private int movieId;

   @Before
   public void setUp() throws Exception {
      MockitoAnnotations.initMocks(this);
      movieId = (new Random()).nextInt(19);
      movieArticlesBehaviorSubject = BehaviorSubject.createDefault(getMockData());
      when(movieRepository.getMovieArticles()).thenReturn(movieArticlesBehaviorSubject);
      detailsViewModel = new DetailsViewModel(application);
      detailsViewModel.setMovieId(movieId);
   }

   @After
   public void tearDown() throws Exception {
      detailsViewModel.clearReferences();
   }

   @Test
   public void testGetBackDropImageURL() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertEquals(article.getBackdrop_path(), detailsViewModel.getBackDropImageURLLiveData().getValue());
   }

   @Test
   public void testGetMovieTitle() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertEquals(article.getTitle(), detailsViewModel.getMovieTitleLiveData().getValue());
   }

   @Test
   public void testGetMovieOverView() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertEquals(article.getOverview(), detailsViewModel.getMovieOverViewLiveData().getValue());
   }

   @Test
   public void testGetMovieRating() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertTrue(article.getVote_average() == detailsViewModel.getMovieRatingLiveData().getValue());
   }

   @Test
   public void testGetVotes() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertTrue(article.getVote_count() == detailsViewModel.getVotesLiveData().getValue());
   }

   @Test
   public void testGetMovieReleaseDate() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertEquals(article.getRelease_date(), detailsViewModel.getMovieReleaseDateLiveData().getValue());
   }

   @Test
   public void testGetMovieGenere() {
      List<Article> articles = getMockData();
      Article article = articles.get(movieId);
      assertEquals(article.getGenre_ids(), detailsViewModel.getMovieGenereLiveData().getValue());
   }

   private List<Article> getMockData() {
      List<Article> articles = new ArrayList<>();
      for (int i = 0; i < 20; i++) {
         Article article = new Article();
         article.setAdult(false);
         if (i % 5 == 0) {
            article.setBackdrop_path(backDropPath[0]);
            article.setPoster_path(posterPath[0]);
         }
         else if (i % 5 == 1) {
            article.setBackdrop_path(backDropPath[1]);
            article.setPoster_path(posterPath[1]);
         }
         else if (i % 5 == 2) {
            article.setBackdrop_path(backDropPath[2]);
            article.setPoster_path(posterPath[2]);
         }
         else if (i % 5 == 3) {
            article.setBackdrop_path(backDropPath[3]);
            article.setPoster_path(posterPath[3]);
         }
         else if (i % 5 == 4) {
            article.setBackdrop_path(backDropPath[4]);
            article.setPoster_path(posterPath[4]);
         }
         List<Integer> genreIds = new ArrayList<>();
         genreIds.add(16);
         genreIds.add(28);
         if (i % 2 == 0) {
            genreIds.add(14);
         }
         if (i % 3 == 0) {
            genreIds.add(27);
         }
         article.setGenre_ids(genreIds);
         article.setId(550 + i);
         article.setOriginal_language("en");
         article.setOriginal_title("Movie " + i);
         article.setOverview("This is Movie " + i + ". It is multi-lingual movie based on crime thriller");
         article.setPopularity(25632D);
         if (i < 10) {
            article.setRelease_date("200" + i + "21-04-0" + i);
         }
         else {
            article.setRelease_date("20" + i + "21-04-" + i);
         }
         article.setTitle("Movie " + i);
         article.setVideo(true);
         article.setVote_average(215D + i);
         article.setVote_count(100 + 1);
         articles.add(article);
      }
      return articles;
   }
}