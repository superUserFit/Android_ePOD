package com.example.epod.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context applicationContext;

    public AppModule(Context context) {
        this.applicationContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(Context context, String preferenceName) {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }
}
