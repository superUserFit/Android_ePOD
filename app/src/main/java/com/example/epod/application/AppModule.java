package com.example.epod.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.epod.dependency_injection.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context applicationContext;

    public AppModule(@ApplicationContext Context context) {
        this.applicationContext = context;
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideContext() {
        return applicationContext;
    }

    @Provides
    @Singleton
    public String providePreferenceName() {
        return "e_pod";
    }

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(@ApplicationContext Context context, String preferenceName) {
        return context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
    }
}
