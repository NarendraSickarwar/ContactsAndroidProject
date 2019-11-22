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

package com.app.contactsandroidproject.ui.main.sendmessage;

import android.util.Log;

import com.app.contactsandroidproject.data.DataManager;
import com.app.contactsandroidproject.data.model.api.SmsRequest;
import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.ui.main.base.BaseViewModel;
import com.app.contactsandroidproject.utils.rx.SchedulerProvider;


/**
 * Created by narendra on 19/11/19.
 */

public class SendMessageViewModel extends BaseViewModel<SendMessageCallback> {

    private static final String TAG = SendMessageViewModel.class.getSimpleName();

    public SendMessageViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        // UpdateMessagesCards();
    }

    public void onLaterClick() {
        getNavigator().dismissDialog();
    }

    public void onSubmitClick() {
        getNavigator().dismissDialog();
    }

    public void updateMessagesCards(Contact contact) {

        getCompositeDisposable().add(getDataManager()
                .getUpdateContacts(contact)

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(contactList -> {
                    if (contactList != null) {
                        Log.d(TAG, "loadContactCards: ");

                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    Log.d(TAG, "loadContactCards: " + throwable);
                }));
    }

    public void sendSMS(String to, String from, String body) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .doServerSmsApiCall(new SmsRequest.ServersmsRequest(to, from, body))

                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {

                    if (response != null && response.getSid() != null) {
                        getNavigator().updateDataInDb();
                    }

                    setIsLoading(false);
                    // getNavigator().openMainActivity();
                }, throwable -> {
                    setIsLoading(false);
                    // getNavigator().handleError(throwable);
                }));
    }

    public interface SendMessageItemViewModelListener {

        void sendSmsSucess(String randomno);
    }
}
