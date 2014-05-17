package com.dleibovich;

import android.app.Application;

public class KSCApplication extends Application {

    private static AppPreferences mPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        mPreferences = new AppPreferences(getApplicationContext());
    }

    public static AppPreferences getPreferences() {
        return mPreferences;
    }
}
