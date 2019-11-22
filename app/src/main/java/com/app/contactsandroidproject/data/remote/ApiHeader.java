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

package com.app.contactsandroidproject.data.remote;

import com.app.contactsandroidproject.di.ApiInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by narendra on 19/11/19.
 */

@Singleton
public class ApiHeader {


    private PublicApiHeader mPublicApiHeader;

    @Inject
    public ApiHeader(PublicApiHeader publicApiHeader) {
        mPublicApiHeader = publicApiHeader;

    }


    public PublicApiHeader getPublicApiHeader() {
        return mPublicApiHeader;
    }


    public static final class PublicApiHeader {
        @Expose
        @SerializedName("Authorization")
        private String Authorization;

        @Expose
        @SerializedName("Content-Type")
        private String Content_Type;
        //application/x-www-form-urlencoded

        @Inject
        public PublicApiHeader(@ApiInfo String Authorization) {
            this.Authorization = Authorization;
            this.Content_Type = "application/x-www-form-urlencoded";
        }


        public String getAuthorization() {
            return Authorization;
        }

        public void setAuthorization(String authorization) {
            Authorization = authorization;
        }


        public String getContent_Type() {
            return Content_Type;
        }

        public void setContent_Type(String content_Type) {
            Content_Type = content_Type;
        }


    }
}
