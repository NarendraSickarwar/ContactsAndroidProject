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

package com.app.contactsandroidproject.data.model.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.app.contactsandroidproject.utils.DateConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by narendra on 19/11/19.
 */
@Entity(tableName = "Contacts")
public class Contact implements Serializable {

    @Expose
    @SerializedName("name")
    @ColumnInfo(name = "name")
    public String name;
    @Expose
    @PrimaryKey
    public Long id;
    @Expose
    @SerializedName("dial_code")
    @ColumnInfo(name = "dial_code")
    public String dialCode;
    @Expose
    @SerializedName("code")
    @ColumnInfo(name = "code")
    public String code;
    @Expose
    @SerializedName("mobile")
    @ColumnInfo(name = "mobile")
    public String mobile;
    @Expose
    @SerializedName("send_msg")
    @ColumnInfo(name = "send_msg")
    public boolean sendMsg;
    @Expose
    @SerializedName("created_At")
    @ColumnInfo(name = "created_At")
    @TypeConverters({DateConverter.class})

    public Date created_At;
    @Expose
    @SerializedName("otp")
    @ColumnInfo(name = "otp")
    public String otp;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSendMsg(boolean sendMsg) {
        this.sendMsg = sendMsg;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }

        Contact contact = (Contact) o;

        if (!name.equals(contact.name)) {
            return false;
        }
        if (!dialCode.equals(contact.dialCode)) {
            return false;
        }
        if (!id.equals(contact.id)) {
            return false;
        }
        if (!mobile.equals(contact.mobile)) {
            return false;
        }
        if (!created_At.equals(contact.created_At)) {
            return false;
        }
        return otp.equals(contact.otp);// return date.equals(blog.date);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dialCode.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + created_At.hashCode();
        result = 31 * result + otp.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreatedAt() {
        return created_At;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getID() {
        return id;
    }

    public boolean getsendMsg() {
        return sendMsg;
    }


}
