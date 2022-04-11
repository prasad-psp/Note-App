package com.psp.note_app.viewmodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psp.note_app.repo.MainRepo;

public class MainViewModel extends ViewModel {

    private final MainRepo repo;

    private final MutableLiveData<Boolean> showNotification = new MutableLiveData<>();

    public MainViewModel(Context context) {
        repo = new MainRepo(context);
    }

    public MutableLiveData<Boolean> getShowNotificationObserver() {
        return showNotification;
    }

    public void isFirstTimeShowNotification(boolean firstTimeShow) {
        repo.isFirstTimeShowNotification(firstTimeShow);

        if(firstTimeShow) {
            showNotification.setValue(false);
        }
    }

    public void checkWelcomeNotification() {
        if(!repo.isAlreadyShowNotification()) {
            showNotification.setValue(true);
        }
    }

}
