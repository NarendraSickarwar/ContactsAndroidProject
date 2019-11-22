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

package com.app.contactsandroidproject.di.builder;

import com.app.contactsandroidproject.ui.main.contactlist.ContactFragmentProvider;
import com.app.contactsandroidproject.ui.main.main.MainActivity;
import com.app.contactsandroidproject.ui.main.main.MainActivityModule;
import com.app.contactsandroidproject.ui.main.messagelist.MessageFragmentProvider;
import com.app.contactsandroidproject.ui.main.sendmessage.SendMessageDialogProvider;
import com.app.contactsandroidproject.ui.main.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by narendra on 19/11/19.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            ContactFragmentProvider.class,
            MessageFragmentProvider.class,
            SendMessageDialogProvider.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

}
