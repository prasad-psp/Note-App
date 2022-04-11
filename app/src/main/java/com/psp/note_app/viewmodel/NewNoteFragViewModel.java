package com.psp.note_app.viewmodel;

import static com.psp.note_app.utils.Constants.DESC_KEY;
import static com.psp.note_app.utils.Constants.ID_KEY;
import static com.psp.note_app.utils.Constants.TITLE_KEY;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.psp.note_app.model.Note;
import com.psp.note_app.repo.NewNoteFragRepo;
import com.psp.note_app.utils.DateUtils;

public class NewNoteFragViewModel extends ViewModel {

    private final NewNoteFragRepo repo; // Repo object

    private int id = 0;
    private final MutableLiveData<String> title = new MutableLiveData<>();
    private final MutableLiveData<String> desc = new MutableLiveData<>();

    private boolean isAlreadyRun = false;

    public NewNoteFragViewModel(Context context) {
        // init repo
        repo = new NewNoteFragRepo(context);
    }

    public MutableLiveData<String> getTitleObserver() {
        return title;
    }

    public MutableLiveData<String> getDescObserver() {
        return desc;
    }

    public void addNote(String title, String desc) {
        addNote(0, title, desc);
    }

    public void addNote(int id, String title, String desc) {
        Note note = new Note();
        note.setId(id);
        note.setTitle(title);
        note.setDesc(desc);
        note.setDate(DateUtils.getCurrDate());
        note.setTime(DateUtils.getCurrTime());
        repo.addNote(note);
    }

    public void setValues(Bundle bundle) {
        if(!isAlreadyRun) {
            isAlreadyRun = true;
            id = getId(bundle);

            if(id != 0) {
                String title = getTitle(bundle);
                String desc = getDesc(bundle);

                if(!title.isEmpty()) {
                    this.title.setValue(title);
                }

                if(!desc.isEmpty()) {
                    this.desc.setValue(desc);
                }

            }
        }
    }

    public boolean isExistingNote() {
        return id != 0;
    }

    public int getExistingId() {
        return id;
    }

    private int getId(Bundle bundle) {
        if(bundle != null) {
            return bundle.getInt(ID_KEY);
        }
        return 0;
    }

    private String getTitle(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(TITLE_KEY);
        }
        return  "";
    }

    private String getDesc(Bundle bundle) {
        if(bundle != null) {
            return bundle.getString(DESC_KEY);
        }
        return "";
    }
}
