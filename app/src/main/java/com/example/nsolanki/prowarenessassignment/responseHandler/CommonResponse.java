package com.example.nsolanki.prowarenessassignment.responseHandler;

import android.content.Context;

import com.android.volley.Response;
import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.interfaces.OnResponseReceived;
import com.example.nsolanki.prowarenessassignment.response.NamesListResponse;

public class CommonResponse implements Response.Listener {

    private static final String TAG = CommonResponse.class.getSimpleName();
    private ModelEnums modelEnums;
    private Context context;
    private OnResponseReceived onResponseReceived;


    public CommonResponse(Context context, ModelEnums modelEnums, OnResponseReceived onResponseReceived) {
        this.modelEnums = modelEnums;
        this.context = context;
        this.onResponseReceived = onResponseReceived;
    }

    @Override
    public void onResponse(Object response) {
        switch (modelEnums) {
            case NAMES_LIST:
                new NamesListResponse(this.context, this.modelEnums,this.onResponseReceived).onResponse(response);
                break;
            default:
                break;
        }
    }
}