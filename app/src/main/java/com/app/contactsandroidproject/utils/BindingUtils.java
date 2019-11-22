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

package com.app.contactsandroidproject.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.ui.main.contactlist.ContactAdapter;
import com.app.contactsandroidproject.ui.main.messagelist.MessageAdapter;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by narendra on 19/11/19.
 */

public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter({"adapter"})
    public static void addContactItems(RecyclerView recyclerView, List<Contact> contacts) {
        if (recyclerView.getAdapter() instanceof ContactAdapter) {
            ContactAdapter adapter = (ContactAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(contacts);
            }
        } else {
            MessageAdapter adapter = (MessageAdapter) recyclerView.getAdapter();
            if (adapter != null) {
                adapter.clearItems();
                adapter.addItems(contacts);
            }
        }
    }


    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
