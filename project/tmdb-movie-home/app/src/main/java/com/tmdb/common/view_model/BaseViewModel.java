/*
 * Copyright (C) 2021.
 * (put copyright information)
 */

package com.tmdb.common.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Base {@link AndroidViewModel} class for all ViewModels in app. Class handles unsubscribing from {@link io.reactivex.Observable}
 * datasources.
 */
public abstract class BaseViewModel extends AndroidViewModel {

    private CompositeDisposable compositeDisposable;

    public BaseViewModel(@NonNull Application application) {
        super(application);
        compositeDisposable = new CompositeDisposable();
    }

    /**
     * Add a subscription to be cleared when the viewmodel is no longer in use.
     * @param disposable
     */
    protected void addDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
        clearReferences();
    }

    protected abstract void clearReferences();
}
