package com.psp.note_app.repo;

import android.content.Context;

import com.psp.note_app.preference.MainPreference;

public class MainRepo {

    private final MainPreference preference;

    public MainRepo(Context context) {
        preference = new MainPreference(context);
    }

    public boolean isAlreadyShowNotification() {
        return preference.isAlreadyShowNotification();
    }

    public void isFirstTimeShowNotification(boolean firstTimeShow) {
        preference.isFirstTimeShowNotification(firstTimeShow);
    }
}
