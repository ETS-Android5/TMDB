package com.tmdb.data.repository;

import com.tmdb.data.model.Article;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class ArticleRepository implements MovieRepository {
   private static final String TAG = ArticleRepository.class.getSimpleName();
   private BehaviorSubject movieListSubject;
   private List<Article> data;
   private String[] backDropPath = { "/6ELCZlTA5lGUops70hKdB83WJxH.jpg", "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg", "/ouOojiypBE6CD1aqcHPVq7cJf2R.jpg", "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
      "/lkInRiMtLgl9u9xE0By5hqf66K8.jpg" };
   private String[] posterPath = { "/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg", "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg", "/xCEg6KowNISWvMh8GvPSxtdf9TO.jpg", "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
      "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg" };

   public ArticleRepository() {
      movieListSubject = BehaviorSubject.create();
      movieListSubject.onNext(getMockData());
   }

   @Override
   public Observable<List<Article>> getMovieArticles() {
      return movieListSubject;
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
