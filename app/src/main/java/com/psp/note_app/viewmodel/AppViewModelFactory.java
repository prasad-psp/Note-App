package com.psp.note_app.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AppViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    private LifecycleOwner lifecycleOwner;

    public AppViewModelFactory(Context context) {
        this.context = context;
    }

    public AppViewModelFactory(Context context, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass == MainViewModel.class) {
            MainViewModel mainViewModel = new MainViewModel(context);
            return (T) mainViewModel;
        }
        else if(modelClass == HomeViewModel.class) {
            HomeViewModel homeViewModel = new HomeViewModel(context,lifecycleOwner);
            return (T) homeViewModel;
        }
        else if(modelClass == NewNoteViewModel.class) {
            NewNoteViewModel newNoteViewModel = new NewNoteViewModel(context);
            return (T) newNoteViewModel;
        }
        else {
            return null;
        }
    }
}
