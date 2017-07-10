package com.example.nsolanki.prowarenessassignment.response;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.interfaces.OnResponseReceived;
import com.example.nsolanki.prowarenessassignment.model.BaseDataModel;
import com.example.nsolanki.prowarenessassignment.responseHandler.CommonResponse;
import com.example.nsolanki.prowarenessassignment.responseHandler.ResponseDispatcher;
import com.example.nsolanki.prowarenessassignment.responseHandler.ResponseHandler;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class NamesListResponse implements Response.Listener {

    private static final String TAG = NamesListResponse.class.getSimpleName();
    private Context context;
    private ModelEnums modelEnums;
    private OnResponseReceived onResponseReceived;
    private ResponseHandler responseHandler;
    private BaseDataModel baseDataModel;

    public NamesListResponse(Context context, ModelEnums modelEnums, OnResponseReceived onResponseReceived) {
        this.context = context;
        this.modelEnums = modelEnums;
        this.onResponseReceived = onResponseReceived;
        responseHandler = new ResponseDispatcher();
    }

    @Override
    public void onResponse(Object response) {
        // Add the response handling part in here//
        // TODO : Add the code here for parsing of the response received from the webservice
        // TODO : and then passing it to cooresponding mediator//
        Log.d(TAG, "Response : " + response.toString());
        baseDataModel = responseHandler.mapResponseToCorrespondingObject(this.context, response.toString(), ModelEnums.NAMES_LIST);
        onResponseReceived.responseReceived(baseDataModel);
    }
}
