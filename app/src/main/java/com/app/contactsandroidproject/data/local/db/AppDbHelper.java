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

package com.app.contactsandroidproject.data.local.db;

import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.utils.DateConverter;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by narendra on 19/11/19.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<List<Contact>> getAllContacts() {
        return mAppDatabase.contactDao().loadAll()
                .toObservable();
    }


    @Override
    public Observable<Boolean> isContactsEmpty() {
        return mAppDatabase.contactDao().loadAll()
                .flatMapObservable(contacts -> Observable.just(contacts.isEmpty()));

    }


    @Override
    public Observable<Boolean> saveContact(final Contact contact) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.contactDao().insert(contact);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> updateContact(Contact contacts) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.contactDao().updateContact(contacts.getsendMsg(), contacts.getOtp(), contacts.getID(), DateConverter.dateToTimestamp(contacts.getCreatedAt()));
                return true;
            }
        });


    }

    @Override
    public Observable<Boolean> saveContactList(final List<Contact> contactList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.contactDao().insertAll(contactList);
                return true;
            }
        });
    }

    @Override
    public Observable<List<Contact>> getSendMessageContactList(boolean sendmsg) {

        return mAppDatabase.contactDao().getContacts(sendmsg).toObservable();

    }
}
