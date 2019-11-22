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

package com.app.contactsandroidproject.data;

import android.content.Context;

import com.app.contactsandroidproject.data.local.db.DbHelper;
import com.app.contactsandroidproject.data.local.prefs.PreferencesHelper;
import com.app.contactsandroidproject.data.model.api.SmsRequest;
import com.app.contactsandroidproject.data.model.api.SmsResponse;
import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.data.remote.ApiHeader;
import com.app.contactsandroidproject.data.remote.ApiHelper;
import com.app.contactsandroidproject.utils.AppConstants;
import com.app.contactsandroidproject.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by narendra on 19/11/19.
 */
@Singleton
public class AppDataManager implements DataManager {

    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }


    @Override
    public Single<SmsResponse> doServerSmsApiCall(SmsRequest.ServersmsRequest request) {
        return mApiHelper.doServerSmsApiCall(request);
    }

    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {

    }


    @Override
    public Observable<List<Contact>> getAllContacts() {
        return mDbHelper.getAllContacts();
    }


    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }


    @Override
    public Observable<List<Contact>> getContactCardData() {
        return mDbHelper.getAllContacts()
                .flatMap(contact -> Observable.fromIterable(contact))

                .toList()
                .toObservable();
    }

    @Override
    public Observable<List<Contact>> getsendMessageContactCardData(boolean sendMsg) {
        return mDbHelper.getSendMessageContactList(sendMsg)
                .flatMap(contact -> Observable.fromIterable(contact))

                .toList()
                .toObservable();
    }

    @Override
    public Observable<Boolean> getUpdateContacts(Contact contact) {
        return mDbHelper.updateContact(contact);
    }


    @Override
    public Observable<Boolean> isContactsEmpty() {
        return mDbHelper.isContactsEmpty();
    }


    @Override
    public Observable<Boolean> saveContact(Contact contact) {
        return mDbHelper.saveContact(contact);
    }

    @Override
    public Observable<Boolean> updateContact(Contact contacts) {
        return mDbHelper.updateContact(contacts);
    }

    @Override
    public Observable<Boolean> saveContactList(List<Contact> contactList) {
        return mDbHelper.saveContactList(contactList);
    }

    @Override
    public Observable<List<Contact>> getSendMessageContactList(boolean sendmsg) {
        return mDbHelper.getSendMessageContactList(sendmsg);
    }


    @Override
    public Observable<Boolean> seedDatabaseContacts() {
        return mDbHelper.isContactsEmpty()
                .concatMap(isEmpty -> {
                    if (isEmpty) {
                        Type type = $Gson$Types.newParameterizedTypeWithOwner(null, List.class, Contact.class);
                        List<Contact> contactList = mGson
                                .fromJson(CommonUtils.loadJSONFromAsset(mContext, AppConstants.SEED_DATABASE_QUESTIONS), type);
                        return saveContactList(contactList);
                    }
                    return Observable.just(false);
                });
    }


    @Override
    public void updateSMSInfo(String sid) {

    }


}
