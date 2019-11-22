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

import androidx.databinding.ObservableField;

import com.app.contactsandroidproject.data.model.db.Contact;

/**
 * Created by narendra on 19/11/19.
 */

public class ContactItemViewModel {

    public final ObservableField<String> name;

    public final ObservableField<String> dialcode;

    public final ObservableField<String> code;

    public final ObservableField<String> mobile;

    public final ContactItemViewModelListener mListener;

    public final ObservableField<String> imageUrl;

    private final Contact mcontact;

    public ContactItemViewModel(Contact contact, ContactItemViewModelListener listener) {
        this.mcontact = contact;
        this.mListener = listener;
        imageUrl = new ObservableField<>("http://www.suitdoctors.com/wp-content/uploads/2016/11/dummy-man-570x570.png");
        name = new ObservableField<>(contact.getName());
        dialcode = new ObservableField<>(contact.getDialCode());
        code = new ObservableField<>(contact.getCode());
        mobile = new ObservableField<>(contact.getMobile());
    }

    public void onItemClick() {
        mListener.onItemClick(mcontact.getName(), mcontact.getMobile(), mcontact);
    }

    public interface ContactItemViewModelListener {

        void onItemClick(String name, String mobileno, Contact contact);
    }
}
