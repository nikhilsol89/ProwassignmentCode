package com.example.nsolanki.prowarenessassignment.request;

import android.content.Context;
import android.os.PowerManager;

import java.util.HashMap;

public abstract class AbstractVolleyMediator {

    public static final String TAG = AbstractVolleyMediator.class.getSimpleName();

    HashMap<String, String> headers = new HashMap<>();
    public Context objContext;
    PowerManager.WakeLock wakeLock;
    protected boolean isCancelApplicable = false;

    public AbstractVolleyMediator() {

    }

    public AbstractVolleyMediator(Context context) {
        this();
        this.objContext = context;
    }

    public void volleyCustomPreExecute() {
        PowerManager pm = (PowerManager) this.objContext.getSystemService(Context.POWER_SERVICE);
        this.wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "PlaceGuideWakeLock");
        this.wakeLock.acquire();
    }

    public void volleyCustomPostExecute() {
        try {
            if (this.wakeLock != null) {
                if (this.wakeLock.isHeld()) this.wakeLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void onCancelled() {
        try {
            if (this.wakeLock != null) {
                if (this.wakeLock.isHeld()) this.wakeLock.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isCancelApplicable() {
        return isCancelApplicable;
    }

    public void setCancelApplicable(boolean cancelApplicable) {
        isCancelApplicable = cancelApplicable;
    }

    public abstract void makeVolleyRequest();

    public abstract void cancelMediator();

    public abstract void startProgressBar();
}
