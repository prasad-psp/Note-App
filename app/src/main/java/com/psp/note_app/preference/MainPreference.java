package com.psp.note_app.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class MainPreference {

    private final String PREFERENCE = "NotificationPref";
    private final String PREFERENCE_KEY = "noti_key";

    private final SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public MainPreference(Context context) {
        preferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
    }


    public boolean isAlreadyShowNotification() {
        return preferences.getBoolean(PREFERENCE_KEY,false);
    }

    public void isFirstTimeShowNotification(boolean firstTimeShow) {
        if(editor == null) {
            editor = preferences.edit();
        }

        editor.putBoolean(PREFERENCE_KEY, firstTimeShow);

        if(editor != null) {
            editor.commit();
            editor = null;
        }
    }
}
