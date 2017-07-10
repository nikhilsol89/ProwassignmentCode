package com.example.nsolanki.prowarenessassignment.responseHandler;

import android.content.Context;

import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.model.BaseDataModel;


/**
 * Created by nsolanki on 2/17/2017.
 */

public abstract class ResponseHandler {

    private static final String TAG = ResponseHandler.class.getSimpleName();

    public abstract BaseDataModel mapResponseToCorrespondingObject(Context context, String jsonString, ModelEnums modelEnums);
}
