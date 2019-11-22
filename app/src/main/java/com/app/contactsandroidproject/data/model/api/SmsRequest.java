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

package com.app.contactsandroidproject.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by narendra on 19/11/19.
 */

public final class SmsRequest {

    private SmsRequest() {
        // This class is not publicly instantiable
    }


    public static class ServersmsRequest {

        @Expose
        @SerializedName("To")
        private String to;

        @Expose
        @SerializedName("From")
        private String from;
        @Expose
        @SerializedName("Body")
        private String body;


        public ServersmsRequest(String to, String from, String body) {
            this.to = to;
            this.from = from;
            this.body = body;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ServersmsRequest that = (ServersmsRequest) object;

            if (to != null ? !to.equals(that.to) : that.to != null) {
                return false;
            }
            return from != null ? from.equals(that.from) : that.from == null;
        }

        @Override
        public int hashCode() {
            int result = to != null ? to.hashCode() : 0;
            result = 31 * result + (from != null ? from.hashCode() : 0);
            return result;
        }

        public String getTO() {
            return to;
        }

        public String getFrom() {
            return to;
        }
    }
}
