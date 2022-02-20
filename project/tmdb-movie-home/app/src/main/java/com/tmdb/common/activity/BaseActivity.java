/*
 * Copyright (C) 2021. All rights reserved.
 * (put copyright information)
 */

package com.tmdb.common.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Base class for all activity
 */
@SuppressWarnings({"squid:MaximumInheritanceDepth"})
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
    }
}
