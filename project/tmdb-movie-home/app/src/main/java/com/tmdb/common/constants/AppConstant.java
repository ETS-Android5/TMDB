/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */

package com.tmdb.common.constants;

import com.google.common.collect.ImmutableMap;
import com.tmdb.movieshome.R;

import java.util.Map;

/**
 * Constants file
 */
public class AppConstant {

    public static final String BASE_URL = "https://api.themoviedb.org/";
    public static final String IMAGE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "367d23f78f6f44d3f1784eb139d16985";

    // To map genere id with respective string res id for localization
    public static final Map<Integer, Integer> GENERE_ID_MAPPER = ImmutableMap.<Integer, Integer>builder()
       .put(12, R.string.genere_id_12)
       .put(14, R.string.genere_id_14)
       .put(16, R.string.genere_id_16)
       .put(18, R.string.genere_id_18)
       .put(27, R.string.genere_id_27)
       .put(28, R.string.genere_id_28)
       .put(35, R.string.genere_id_35)
       .put(53, R.string.genere_id_53)
       .put(80, R.string.genere_id_80)
       .put(878, R.string.genere_id_878)
       .put(9648, R.string.genere_id_9648)
       .put(10751, R.string.genere_id_10751)
       .put(10752, R.string.genere_id_10752)
       .build();
}
