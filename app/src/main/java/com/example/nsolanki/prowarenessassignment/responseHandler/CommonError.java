package com.example.nsolanki.prowarenessassignment.responseHandler;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.nsolanki.prowarenessassignment.interfaces.OnErrorReceived;

import java.net.ConnectException;


/**
 * Created by nsolanki on 2/10/2017.
 */

public class CommonError implements Response.ErrorListener {

    private static final String TAG = CommonError.class.getSimpleName();

    private Context context;
    private OnErrorReceived mOnErrorReceived;

    public CommonError(Context context, OnErrorReceived onErrorReceived) {
        this.context = context;
        this.mOnErrorReceived = onErrorReceived;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // TODO : Need to do the complete error handling for this class//
        Log.e(TAG, "CommonError ==  onErrorResponse   cause == " + error.getCause() + "   message == " + error.getMessage() + "   class == " + error.getClass());
        if (error instanceof NetworkError) {

        } else if (error instanceof ServerError) {

        } else if (error instanceof NoConnectionError) {

        } else if (error instanceof AuthFailureError) {

        } else if (error instanceof ParseError) {

        } else if (error instanceof TimeoutError) {

        } else {

        }
        if (error.getCause() instanceof ConnectException) {

        }
        mOnErrorReceived.onErrorReceived(error);
        //Util.showToast(context, msg);
    }
}
