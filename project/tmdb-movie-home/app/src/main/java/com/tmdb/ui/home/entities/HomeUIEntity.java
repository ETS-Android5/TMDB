/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.ui.home.entities;

import org.apache.commons.lang3.StringUtils;

/**
 * Entity class to hold movie article data to be displayed
 */
public class HomeUIEntity {
   private int Id;
   private String title;
   private String movieOverView;
   private String urlToPosterImage;
   private int votes;
   private double rating;

   public HomeUIEntity(int Id, String title, String movieOverView, String urlToImage, int votes, double rating) {
      this.Id = Id;
      this.title = title;
      this.movieOverView = movieOverView;
      this.urlToPosterImage = urlToImage;
      this.votes = votes;
      this.rating = rating;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      HomeUIEntity that = (HomeUIEntity) o;
      return getId() == that.getId() &&
         StringUtils.equals(getTitle(), that.getTitle()) &&
         StringUtils.equals(getMovieOverView(), that.getMovieOverView()) &&
         StringUtils.equals(getUrlToPosterImage(), that.getUrlToPosterImage()) &&
         getVotes() == that.getVotes() &&
         getRating() == that.getRating() ;
   }

   @Override
   public int hashCode() {
      int hash = 17;
      hash = 31 * hash + getId();
      hash = 31 * hash + (getTitle() == null ? 0 : getTitle().hashCode());
      hash = 31 * hash + (getMovieOverView() == null ? 0 : getMovieOverView().hashCode());
      hash = 31 * hash + (getUrlToPosterImage() == null ? 0 : getUrlToPosterImage().hashCode());
      hash = 31 * hash + getVotes();
      return hash;
   }

   public int getId() {
      return Id;
   }

   public String getTitle() {
      return title;
   }

   public String getMovieOverView() {
      return movieOverView;
   }

   public String getUrlToPosterImage() {
      return urlToPosterImage;
   }

   public int getVotes() {
      return votes;
   }

   public double getRating() {
      return rating;
   }
}
