package com.example.nsolanki.prowarenessassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nsolanki on 7/10/2017.
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactDetailsModel extends BaseDataModel implements Parcelable {

    @JsonProperty("name")
    String name;
    @JsonProperty("uid")
    int uid;

    int isDeleted = 0;
    public ContactDetailsModel(){}

    protected ContactDetailsModel(Parcel in) {
        name = in.readString();
        uid = in.readInt();
    }

    public static final Creator<ContactDetailsModel> CREATOR = new Creator<ContactDetailsModel>() {
        @Override
        public ContactDetailsModel createFromParcel(Parcel in) {
            return new ContactDetailsModel(in);
        }

        @Override
        public ContactDetailsModel[] newArray(int size) {
            return new ContactDetailsModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(uid);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
