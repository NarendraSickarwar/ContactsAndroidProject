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

public final class SmsResponse {


    /**
     * sid : SM3d3940c8cc704ae894abead4faee0890
     * date_created : Mon, 18 Nov 2019 11:01:04 +0000
     * date_updated : Mon, 18 Nov 2019 11:01:04 +0000
     * date_sent : null
     * account_sid : ACb67288182d2054aff41562f29e1f96dd
     * to : +917014107167
     * from : +12565989256
     * messaging_service_sid : null
     * body : Sent from your Twilio trial account - Hi, your otp is 445016
     * status : queued
     * num_segments : 1
     * num_media : 0
     * direction : outbound-api
     * api_version : 2010-04-01
     * price : null
     * price_unit : USD
     * error_code : null
     * error_message : null
     * uri : /2010-04-01/Accounts/ACb67288182d2054aff41562f29e1f96dd/Messages/SM3d3940c8cc704ae894abead4faee0890.json
     * subresource_uris : {"media":"/2010-04-01/Accounts/ACb67288182d2054aff41562f29e1f96dd/Messages/SM3d3940c8cc704ae894abead4faee0890/Media.json"}
     */
    @Expose
    @SerializedName("sid")
    private String sid;
    @Expose
    @SerializedName("date_created")
    private String date_created;
    @Expose
    @SerializedName("date_updated")
    private String date_updated;
    @Expose
    @SerializedName("date_sent")
    private Object date_sent;
    @Expose
    @SerializedName("account_sid")
    private String account_sid;
    @Expose
    @SerializedName("to")
    private String to;
    @Expose
    @SerializedName("from")
    private String from;
    @Expose
    @SerializedName("messaging_service_sid")
    private Object messaging_service_sid;
    @Expose
    @SerializedName("body")
    private String body;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("num_segments")
    private String num_segments;
    @Expose
    @SerializedName("num_media")
    private String num_media;
    @Expose
    @SerializedName("direction")
    private String direction;
    @Expose
    @SerializedName("api_version")
    private String api_version;
    @Expose
    @SerializedName("price")
    private Object price;
    @Expose
    @SerializedName("price_unit")

    private String price_unit;
    @Expose
    @SerializedName("error_code")
    private Object error_code;
    @Expose
    @SerializedName("error_message")
    private Object error_message;
    @Expose
    @SerializedName("uri")
    private String uri;
    @Expose
    @SerializedName("subresource_uris")
    private SubresourceUrisBean subresource_uris;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }

    public Object getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(Object date_sent) {
        this.date_sent = date_sent;
    }

    public String getAccount_sid() {
        return account_sid;
    }

    public void setAccount_sid(String account_sid) {
        this.account_sid = account_sid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Object getMessaging_service_sid() {
        return messaging_service_sid;
    }

    public void setMessaging_service_sid(Object messaging_service_sid) {
        this.messaging_service_sid = messaging_service_sid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNum_segments() {
        return num_segments;
    }

    public void setNum_segments(String num_segments) {
        this.num_segments = num_segments;
    }

    public String getNum_media() {
        return num_media;
    }

    public void setNum_media(String num_media) {
        this.num_media = num_media;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getApi_version() {
        return api_version;
    }

    public void setApi_version(String api_version) {
        this.api_version = api_version;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public String getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(String price_unit) {
        this.price_unit = price_unit;
    }

    public Object getError_code() {
        return error_code;
    }

    public void setError_code(Object error_code) {
        this.error_code = error_code;
    }

    public Object getError_message() {
        return error_message;
    }

    public void setError_message(Object error_message) {
        this.error_message = error_message;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public SubresourceUrisBean getSubresource_uris() {
        return subresource_uris;
    }

    public void setSubresource_uris(SubresourceUrisBean subresource_uris) {
        this.subresource_uris = subresource_uris;
    }


    public static class SubresourceUrisBean {
        /**
         * media : /2010-04-01/Accounts/ACb67288182d2054aff41562f29e1f96dd/Messages/SM3d3940c8cc704ae894abead4faee0890/Media.json
         */

        private String media;

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }
    }
}
