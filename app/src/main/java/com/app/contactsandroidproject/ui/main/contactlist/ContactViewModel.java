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

package com.app.contactsandroidproject.ui.main.contactlist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.contactsandroidproject.data.DataManager;
import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.ui.main.base.BaseViewModel;
import com.app.contactsandroidproject.utils.rx.SchedulerProvider;

import java.util.List;

/**
 * Created by narendra on 19/11/19.
 */
public class ContactViewModel extends BaseViewModel<ContactNavigator> {
    public static final int NO_ACTION = -1, ACTION_ADD_ALL = 0, ACTION_DELETE_SINGLE = 1;

    private static final String TAG = "MainViewModel";
    private final MutableLiveData<List<Contact>> contactListLiveData;
    private int action = NO_ACTION;

    public ContactViewModel(DataManager dataManager,
                            SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);

        contactListLiveData = new MutableLiveData<>();
        loadContactsCards();
        // fetchBlogs();
    }


    public void loadContactsCards() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getContactCardData()
                .doOnNext(list -> Log.d(TAG, "loadContactCards: " + list.size()))
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(contactList -> {
                    if (contactList != null) {
                        Log.d(TAG, "loadContactCards: " + contactList.size());
                        action = ACTION_ADD_ALL;
                        contactListLiveData.setValue(contactList);
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    Log.d(TAG, "loadContactCards: " + throwable);
                }));
    }

    public LiveData<List<Contact>> getContactListLiveData() {
        return contactListLiveData;
    }
}
