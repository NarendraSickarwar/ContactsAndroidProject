package com.app.contactsandroidproject.ui.main.sendmessage;

import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;

import com.app.contactsandroidproject.ViewModelProviderFactory;

import javax.inject.Inject;


public class SendMessageViewItemSendDialog {
    public final ObservableField<String> name;
    public final ObservableField<String> mobile;
    public final ObservableField<String> randomnotxt;
    public final String mobileno;
    public final String randomno;
    SendMessageItemViewModelListener mSendMessageItemViewModelListener;
    @Inject
    ViewModelProviderFactory factory;

    Fragment mContext;

    public SendMessageViewItemSendDialog(Fragment mContext, String name, String mobileno, String randomnotxt, String otp, SendMessageItemViewModelListener mSendMessageItemViewModelListener) {
        this.mSendMessageItemViewModelListener = mSendMessageItemViewModelListener;
        this.mobileno = mobileno;
        this.randomno = randomnotxt;
        this.name = new ObservableField<>(name);
        this.randomnotxt = new ObservableField<>(randomnotxt);
        this.mobile = new ObservableField<>(mobileno);
        this.mContext = mContext;
    }

    public void onLaterClick() {
        mSendMessageItemViewModelListener.dialogdismiss();
    }

    public void onSubmitClick() {
        mSendMessageItemViewModelListener.sendMessage();
    }

    public void onSendMessageClick() {
        //  mSendMessageItemViewModelListener.sendMessage();

        mSendMessageItemViewModelListener.sendSmsuser(randomno);

    }

    public interface SendMessageItemViewModelListener {

        void dialogdismiss();

        void sendMessage();

        void sendSmsuser(String randomno);
    }


}
