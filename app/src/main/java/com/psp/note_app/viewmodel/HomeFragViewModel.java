package com.psp.note_app.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;

import com.psp.note_app.model.Note;

public class HomeFragViewModel extends ViewModel {

    private MutableLiveData<PagingData<Note>> notesLiveData = new MutableLiveData<>();

    private PagingConfig pagingConfig = new PagingConfig(
            20,
            20,
            false,
            20
        );

    public void init() {

    }
}
