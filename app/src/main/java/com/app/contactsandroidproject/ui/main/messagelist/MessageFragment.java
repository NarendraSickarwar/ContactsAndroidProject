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

package com.app.contactsandroidproject.ui.main.messagelist;

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
import com.app.contactsandroidproject.databinding.FragmentMessageBinding;
import com.app.contactsandroidproject.ui.main.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by narendra on 19/11/19.
 */

public class MessageFragment extends BaseFragment<FragmentMessageBinding, MessageViewModel>
        implements MessageNavigator, MessageAdapter.MessageAdapterListener {

    @Inject
    MessageAdapter mContactAdapter;
    FragmentMessageBinding mFragmentMessageBinding;
    @Inject
    LinearLayoutManager mLayoutManager;
    @Inject
    ViewModelProviderFactory factory;
    private MessageViewModel mMessageViewModel;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public MessageViewModel getViewModel() {
        mMessageViewModel = ViewModelProviders.of(this, factory).get(MessageViewModel.class);
        return mMessageViewModel;
    }

    @Override
    public void handleError(Throwable throwable) {
        // handle error
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMessageViewModel.setNavigator(this);
        mContactAdapter.setListener(this);
    }

    @Override
    public void onRetryClick() {
        mMessageViewModel.loadMessagesCards();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentMessageBinding = getViewDataBinding();
        setUp();
    }

    @Override
    public void updateContact(List<Contact> contactList) {
        mContactAdapter.addItems(contactList);
    }

    @SuppressLint("WrongConstant")
    private void setUp() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentMessageBinding.messageRecyclerView.setLayoutManager(mLayoutManager);
        mFragmentMessageBinding.messageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mFragmentMessageBinding.messageRecyclerView.setAdapter(mContactAdapter);
    }


}
