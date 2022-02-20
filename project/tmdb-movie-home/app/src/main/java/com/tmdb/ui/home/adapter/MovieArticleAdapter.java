/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */
package com.tmdb.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.tmdb.movieshome.R;
import com.tmdb.ui.home.entities.HomeUIEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieArticleAdapter extends RecyclerView.Adapter<MovieArticleAdapter.ViewHolder> {

   private List<HomeUIEntity> homeUIEntities;
   private Context context;
   private OnItemClickListener onItemClickListener = null;

   public MovieArticleAdapter(Context context, List<HomeUIEntity> homeUIEntities) {
      this.context = context;
      this.homeUIEntities = homeUIEntities;
   }

   public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
      this.onItemClickListener = onItemClickListener;
   }

   @NonNull
   @Override
   public MovieArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_each_row_movie_article, viewGroup, false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull MovieArticleAdapter.ViewHolder viewHolder, int i) {
      HomeUIEntity entity = homeUIEntities.get(i);
      viewHolder.id = i;
      viewHolder.tvTitle.setText(entity.getTitle());
      String Rating = context.getResources().getString(R.string.details_item_rating);
      String Votes = context.getResources().getString(R.string.details_item_votes);
      viewHolder.tvRatingsAndVotes.setText(Rating + entity.getRating() + "   " + Votes + entity.getVotes());
      viewHolder.tvDescription.setText(entity.getMovieOverView());
      Glide.with(context).load(entity.getUrlToPosterImage()).into(viewHolder.imgViewCover);
   }

   @Override
   public int getItemCount() {
      return homeUIEntities.size();
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      private int id;
      @BindView(R.id.containerLayout) RelativeLayout containerLayout;
      @BindView(R.id.imgViewCover) ImageView imgViewCover;
      @BindView(R.id.tvTitle) TextView tvTitle;
      @BindView(R.id.tvRatingAndVotes) TextView tvRatingsAndVotes;
      @BindView(R.id.tvDescription) TextView tvDescription;

      @OnClick(R.id.containerLayout)
      public void invokeItemClike() {
         if (onItemClickListener != null) {
            onItemClickListener.onItemClick(id);
         }
      }
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         ButterKnife.bind(this, itemView);
      }
   }
}
