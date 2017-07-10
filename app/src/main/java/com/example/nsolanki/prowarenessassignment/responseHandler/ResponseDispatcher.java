package com.example.nsolanki.prowarenessassignment.responseHandler;

import android.content.Context;

import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.model.BaseDataModel;
import com.example.nsolanki.prowarenessassignment.model.ContactListModel;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class ResponseDispatcher extends ResponseHandler {
    private static final String TAG = ResponseDispatcher.class.getSimpleName();
    BaseDataModel baseDataModel;

    @Override
    public BaseDataModel mapResponseToCorrespondingObject(Context context, String jsonString, ModelEnums modelEnums) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        switch (modelEnums) {
            case NAMES_LIST:
                baseDataModel = new ContactListModel();
                try {
                    baseDataModel = objectMapper.readValue(jsonString, ContactListModel.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return baseDataModel;
    }
}
