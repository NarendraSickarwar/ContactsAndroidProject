/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.app.contactsandroidproject.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.app.contactsandroidproject.BuildConfig;
import com.app.contactsandroidproject.R;
import com.app.contactsandroidproject.data.AppDataManager;
import com.app.contactsandroidproject.data.DataManager;
import com.app.contactsandroidproject.data.local.db.AppDatabase;
import com.app.contactsandroidproject.data.local.db.AppDbHelper;
import com.app.contactsandroidproject.data.local.db.DbHelper;
import com.app.contactsandroidproject.data.local.prefs.AppPreferencesHelper;
import com.app.contactsandroidproject.data.local.prefs.PreferencesHelper;
import com.app.contactsandroidproject.data.remote.ApiHelper;
import com.app.contactsandroidproject.data.remote.AppApiHelper;
import com.app.contactsandroidproject.di.ApiInfo;
import com.app.contactsandroidproject.di.DatabaseInfo;
import com.app.contactsandroidproject.di.PreferenceInfo;
import com.app.contactsandroidproject.utils.AppConstants;
import com.app.contactsandroidproject.utils.rx.AppSchedulerProvider;
import com.app.contactsandroidproject.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by narendra on 19/11/19.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    @ApiInfo
    String provideAuthorizationKey() {
        return BuildConfig.Authorization;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    /*@Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,

                preferencesHelper.getAccessToken());
    }*/

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
