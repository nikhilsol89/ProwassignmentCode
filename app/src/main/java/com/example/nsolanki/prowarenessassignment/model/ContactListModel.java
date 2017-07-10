package com.example.nsolanki.prowarenessassignment.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by nsolanki on 7/10/2017.
 */


@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactListModel extends BaseDataModel implements Parcelable {

    @JsonProperty("message")
    String mesage;
    @JsonProperty("status")
    String status;
    @JsonProperty("result")
    List<ContactDetailsModel> contactDetailsModelList;

    public ContactListModel(){}
    protected ContactListModel(Parcel in) {
        mesage = in.readString();
        status = in.readString();
        contactDetailsModelList = in.createTypedArrayList(ContactDetailsModel.CREATOR);
    }

    public static final Creator<ContactListModel> CREATOR = new Creator<ContactListModel>() {
        @Override
        public ContactListModel createFromParcel(Parcel in) {
            return new ContactListModel(in);
        }

        @Override
        public ContactListModel[] newArray(int size) {
            return new ContactListModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mesage);
        parcel.writeString(status);
        parcel.writeTypedList(contactDetailsModelList);
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ContactDetailsModel> getContactDetailsModelList() {
        return contactDetailsModelList;
    }

    public void setContactDetailsModelList(List<ContactDetailsModel> contactDetailsModelList) {
        this.contactDetailsModelList = contactDetailsModelList;
    }
}
