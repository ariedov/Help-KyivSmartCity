package com.dleibovich;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {

    public static final String KEY_USER_NAME = "com.dleibovich.USER_NAME";
    public static final String KEY_USER_PHONE = "com.dleibovich.USER_PHONE";
    public static final String KEY_FILLED_PREFERENCES = "com.dleibovich.FILLED_PREFERENCES";

    private static final String PREFERENCES_NAME = AppPreferences.class.getName();

    private Context mContext;

    AppPreferences(Context context) {
        mContext = context;
    }

    public void setUserData(String userName, String userPhone) {
        SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER_NAME, userName);
        editor.putString(KEY_USER_PHONE, userPhone);
        editor.putBoolean(KEY_USER_PHONE, true);
        editor.commit();
    }

    public String getUserName() {
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getString(KEY_USER_NAME, "");
    }

    public String getUserPhone() {
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getString(KEY_USER_PHONE, "");
    }

    public boolean isSavedUserInfo() {
        return mContext.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE).getBoolean(KEY_FILLED_PREFERENCES, false);
    }
}
