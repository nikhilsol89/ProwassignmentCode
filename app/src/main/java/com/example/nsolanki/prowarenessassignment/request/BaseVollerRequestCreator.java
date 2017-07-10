package com.example.nsolanki.prowarenessassignment.request;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.nsolanki.prowarenessassignment.enums.ModelEnums;
import com.example.nsolanki.prowarenessassignment.enums.ResponseTypeEnum;
import com.example.nsolanki.prowarenessassignment.interfaces.OnErrorReceived;
import com.example.nsolanki.prowarenessassignment.interfaces.OnResponseReceived;
import com.example.nsolanki.prowarenessassignment.responseHandler.CommonError;
import com.example.nsolanki.prowarenessassignment.responseHandler.CommonResponse;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nsolanki on 7/10/2017.
 */

public class BaseVollerRequestCreator {
    public static Request createCorrespondingRequestObject(String webUrl, ResponseTypeEnum responseTypeEnums, final Context context, int requestType, ModelEnums modelEnums, JSONObject putRequestObject, OnResponseReceived onResponseReceived, OnErrorReceived onErrorReceived) {
        switch (responseTypeEnums) {
            case JSON_ARRAY:
                JsonArrayRequest arrayRequest = new JsonArrayRequest(requestType, webUrl, putRequestObject, new CommonResponse(context, modelEnums, onResponseReceived), new CommonError(context, onErrorReceived)) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        RequestParams params = new RequestParams();
                        Map<String, String> headers = new HashMap<>(params.getRequestParams(context));
                        return headers;
                    }
                };
                return arrayRequest;
            case JSON_OBJECT:
                JsonObjectRequest objectRequest = new JsonObjectRequest(requestType, webUrl, putRequestObject, new CommonResponse(context, modelEnums, onResponseReceived), new CommonError(context, onErrorReceived)) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        RequestParams params = new RequestParams();
                        Map<String, String> headers = new HashMap<>(params.getRequestParams(context));
                        return headers;
                    }

                    @Override
                    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        return super.parseNetworkResponse(response);
                    }
                };
                return objectRequest;

            case STRING:
                StringRequest stringRequest = null;
                return stringRequest;
            default:
                return null;


        }
    }


}
