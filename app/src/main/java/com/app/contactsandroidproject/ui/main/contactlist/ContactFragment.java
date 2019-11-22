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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.contactsandroidproject.R;
import com.app.contactsandroidproject.ViewModelProviderFactory;
import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.databinding.FragmentContactBinding;
import com.app.contactsandroidproject.ui.main.base.BaseFragment;
import com.app.contactsandroidproject.ui.main.sendmessage.SendMessageDialog;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by narendra on 19/11/19.
 */

public class ContactFragment extends BaseFragment<FragmentContactBinding, ContactViewModel>
        implements ContactNavigator, ContactAdapter.ContactAdapterListener {

    @Inject
    ContactAdapter mContactAdapter;
    FragmentContactBinding mFragmentContactBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ViewModelProviderFactory factory;
    private ContactViewModel mContactViewModel;

    public static ContactFragment newInstance() {
        Bundle args = new Bundle();
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public ContactViewModel getViewModel() {
        mContactViewModel = ViewModelProviders.of(this, factory).get(ContactViewModel.class);
        return mContactViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactViewModel.setNavigator(this);
        mContactAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mContactViewModel.loadContactsCards();
    }

    @Override
    public void onDialogClick(String name, String mobileno, Contact contact) {
        SendMessageDialog.newInstance(name, mobileno, contact).show(getFragmentManager());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentContactBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void updateContact(List<Contact> contactList) {
        mContactAdapter.addItems(contactList);
    }

    @SuppressLint("WrongConstant")
    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentContactBinding.contactRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentContactBinding.contactRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentContactBinding.contactRecyclerView.setAdapter(mContactAdapter);
    }
}
