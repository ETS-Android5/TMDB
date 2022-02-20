/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.data.repository;

import com.tmdb.data.model.Article;

import java.util.List;

import io.reactivex.Observable;

/**
 * Repository interface for movie data
 */
public interface MovieRepository {
   Observable<List<Article>> getMovieArticles();
}
