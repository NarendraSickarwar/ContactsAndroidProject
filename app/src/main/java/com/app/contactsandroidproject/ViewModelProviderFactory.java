package com.app.contactsandroidproject;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.app.contactsandroidproject.data.DataManager;
import com.app.contactsandroidproject.ui.main.contactlist.ContactViewModel;
import com.app.contactsandroidproject.ui.main.main.MainViewModel;
import com.app.contactsandroidproject.ui.main.messagelist.MessageViewModel;
import com.app.contactsandroidproject.ui.main.sendmessage.SendMessageViewModel;
import com.app.contactsandroidproject.ui.main.splash.SplashViewModel;
import com.app.contactsandroidproject.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by narendra on 19/11/19.
 */
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            //noinspection unchecked
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ContactViewModel.class)) {
            //noinspection unchecked
            return (T) new ContactViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MessageViewModel.class)) {
            //noinspection unchecked
            return (T) new MessageViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(SendMessageViewModel.class)) {
            //noinspection unchecked
            return (T) new SendMessageViewModel(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}