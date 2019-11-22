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

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by narendra on 19/11/19.
 */

public interface DbHelper {

    Observable<List<Contact>> getAllContacts();

    Observable<Boolean> isContactsEmpty();

    Observable<Boolean> saveContact(Contact contacts);

    Observable<Boolean> updateContact(Contact contacts);

    Observable<Boolean> saveContactList(List<Contact> contactList);

    Observable<List<Contact>> getSendMessageContactList(boolean sendmsg);


}
