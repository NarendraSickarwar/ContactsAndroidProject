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

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.app.contactsandroidproject.R;
import com.app.contactsandroidproject.ViewModelProviderFactory;
import com.app.contactsandroidproject.data.model.db.Contact;
import com.app.contactsandroidproject.databinding.DialogSendMessageBinding;
import com.app.contactsandroidproject.ui.main.base.BaseDialog;
import com.app.contactsandroidproject.utils.CommonUtils;

import java.util.Date;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by narendra on 19/11/19.
 */

public class SendMessageDialog extends BaseDialog implements SendMessageCallback, SendMessageViewItemSendDialog.SendMessageItemViewModelListener {

    private static final String TAG = SendMessageDialog.class.getSimpleName();
    static Contact contact;
    static Contact contactSend;
    @Inject
    ViewModelProviderFactory factory;
    DialogSendMessageBinding binding;
    String otp;
    private SendMessageViewModel mSendMessageViewModel;
    private SendMessageViewItemSendDialog messageViewItemSendDialog;

    public static SendMessageDialog newInstance(String name, String mobileno, Contact contact) {
        SendMessageDialog fragment = new SendMessageDialog();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("mobileno", mobileno);
        bundle.putSerializable("contact", contact);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void dismissDialog() {
        dismissDialog(TAG);
    }

    @Override
    public void updateDataInDb() {
        dismissDialog();
        contactSend = new Contact();

        contactSend.setId(contact.getID());
        contactSend.setName(contact.getName());
        contactSend.setCode(contact.getCode());
        contactSend.setDialCode(contact.getDialCode());
        contactSend.setMobile(contact.getMobile());
        contactSend.setSendMsg(true);
        contactSend.setOtp(otp);
        Date d = new Date();
        contactSend.setCreated_At(d);

        mSendMessageViewModel.UpdateMessagesCards(contactSend);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_send_message, container, false);
        View view = binding.getRoot();
        Bundle bundle = getArguments();
        AndroidSupportInjection.inject(this);
        otp = CommonUtils.getRandomNumberString();
        contact = (Contact) bundle.getSerializable("contact");
        mSendMessageViewModel = ViewModelProviders.of(this, factory).get(SendMessageViewModel.class);
        messageViewItemSendDialog = new SendMessageViewItemSendDialog(this, bundle.getString("name"), bundle.getString("mobileno"),
                "Hi, your otp is " + otp, otp,
                this);
        binding.setViewModel(messageViewItemSendDialog);

        mSendMessageViewModel.setNavigator(this);

        return view;
    }

    public void show(FragmentManager fragmentManager) {
        super.show(fragmentManager, TAG);
    }


    @Override
    public void dialogdismiss() {
        dismissDialog();
    }

    @Override
    public void sendMessage() {
        binding.footerButtonLayout.setVisibility(View.GONE);
        binding.txtMessagesend.setVisibility(View.VISIBLE);
        binding.btnSendMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void sendSmsuser(String randomno) {
        mSendMessageViewModel.sendSMS("+917014107167", "+12565989256", randomno);


    }
}
