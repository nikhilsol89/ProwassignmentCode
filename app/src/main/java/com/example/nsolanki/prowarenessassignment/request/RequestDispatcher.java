package com.example.nsolanki.prowarenessassignment.request;

import android.content.Context;

import com.android.volley.Request;
import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.enums.ResponseTypeEnum;
import com.example.nsolanki.prowarenessassignment.interfaces.OnErrorReceived;
import com.example.nsolanki.prowarenessassignment.interfaces.OnResponseReceived;


import org.json.JSONObject;



/**
 * Created by nsolanki on 2/10/2017.
 */

public class RequestDispatcher {

    public static final String TAG = RequestDispatcher.class.getSimpleName();

    private static RequestDispatcher instance;
    private String volleyRequestTAG;
    private String fileName = null;
    private String webUrl = null;
    private int requestType = Request.Method.GET;
    private RequestEnums requestEnum;
    private ModelEnums modelEnums;
    private ResponseTypeEnum responseTypeEnums;
    private JSONObject inputParamsObject;
    private OnResponseReceived onResponseReceived;

    private static BaseVollerRequestCreator baseVolleyRequestCreator;
    private boolean isNetworkAvailable = false;

    private RequestDispatcher() {
    }

    public static RequestDispatcher getInstance() {
        if (instance == null) {
            instance = new RequestDispatcher();
            baseVolleyRequestCreator = new BaseVollerRequestCreator();
        }

        return instance;
    }

    public void setVolleyRequestTAG(String volleyRequestTAG) {
        this.volleyRequestTAG = volleyRequestTAG;
    }

    public ResponseTypeEnum getResponseTypeEnums() {
        return responseTypeEnums;
    }

    public void setResponseTypeEnums(ResponseTypeEnum responseTypeEnums) {
        this.responseTypeEnums = responseTypeEnums;
    }

    public boolean isNetworkAvailable() {
        return isNetworkAvailable;
    }

    public void setNetworkAvailable(boolean networkAvailable) {
        isNetworkAvailable = networkAvailable;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public ModelEnums getModelEnums() {
        return modelEnums;
    }

    public void setModelEnums(ModelEnums modelEnums) {
        this.modelEnums = modelEnums;
    }

    public RequestEnums getRequestEnum() {
        return requestEnum;
    }

    public void setRequestEnum(RequestEnums requestEnum) {
        this.requestEnum = requestEnum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public JSONObject getInputParamsObject() {
        return inputParamsObject;
    }


    public void setInputParamsObject(JSONObject inputParamsObject) {
        this.inputParamsObject = inputParamsObject;
    }

    public void dispatchRequest(final Context context, OnResponseReceived onResponseReceived, OnErrorReceived onErrorReceived) {
        String path = null;
        switch (requestEnum) {
            case ASSET:
                // DO Nothing in here as of now can be used at a later stages//
                break;
            case DATABASE:
                path = "";
                break;
            case NETWORK:

                VolleySingleton.getInstance(context).addToRequestQueue(baseVolleyRequestCreator.createCorrespondingRequestObject(
                        webUrl, responseTypeEnums, context, requestType, modelEnums, inputParamsObject, onResponseReceived, onErrorReceived),volleyRequestTAG);
                break;
            default:
                break;
        }
    }

    public void setRequestType(int request) {
        this.requestType = request;
    }
}// End Of Class//
