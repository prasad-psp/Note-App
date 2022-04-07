package com.psp.note_app.viewmodel;

import android.content.Context;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;

import com.psp.note_app.model.Note;
import com.psp.note_app.repo.HomeFragRepo;

public class HomeFragViewModel extends ViewModel {

    private final MutableLiveData<PagingData<Note>> notesLiveData = new MutableLiveData<>();

    private final PagingConfig pagingConfig = new PagingConfig(
            20,
            20,
            false,
            20
        );

    private HomeFragRepo repo; // Repo object

    public void init(Context context, LifecycleOwner lifecycleOwner) {
        // init repo
        repo = new HomeFragRepo(context);
        // init paging livedata
        PagingLiveData.cachedIn(
                PagingLiveData.getLiveData(new Pager<>(pagingConfig, () -> repo.getNotes())),
                        lifecycleOwner.getLifecycle()
        ).observe(lifecycleOwner, notesLiveData::setValue);
    }


    public LiveData<PagingData<Note>> getNotesObserver() {
        return notesLiveData;
    }

    public void removeNote(int id) {
        repo.removeNote(id);
    }
}
