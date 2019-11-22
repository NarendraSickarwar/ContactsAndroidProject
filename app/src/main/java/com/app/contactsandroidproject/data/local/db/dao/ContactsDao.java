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

package com.app.contactsandroidproject.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.app.contactsandroidproject.data.model.db.Contact;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by narendra on 19/11/19.
 */
@Dao
public interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Contact contact);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Contact> contacts);

    @Query("SELECT * FROM Contacts")
    Single<List<Contact>> loadAll();

    @Query("UPDATE Contacts SET send_msg=:send, otp=:otp, created_At=:CreatedAt WHERE id = :id")
    void updateContact(boolean send, String otp, Long id, String CreatedAt);


    @Query("Select * FROM Contacts WHERE send_msg =:sendmsg ORDER BY created_At DESC ")
    Single<List<Contact>> getContacts(boolean sendmsg);


}
