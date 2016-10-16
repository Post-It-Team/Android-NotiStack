package com.dotgreen.notistack;

import android.app.Application;
import android.content.res.Configuration;

import com.firebase.client.Firebase;

/**
 * Created by hieuapp on 06/10/2016.
 */

public class NotiStackApplication extends Application {

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
