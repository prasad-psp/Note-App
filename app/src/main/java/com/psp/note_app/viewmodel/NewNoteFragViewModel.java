package com.psp.note_app.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;

import com.psp.note_app.model.Note;
import com.psp.note_app.repo.NewNoteFragRepo;
import com.psp.note_app.utils.DateUtils;

public class NewNoteFragViewModel extends ViewModel {

    private NewNoteFragRepo repo; // Repo object

    public void init(Context context) {
        // init repo
        repo = new NewNoteFragRepo(context);
    }

    public void addNote(String title, String desc) {
        Note note = new Note();
        note.setId(0);
        note.setTitle(title);
        note.setDesc(desc);
        note.setDate(DateUtils.getCurrDate());
        note.setTime(DateUtils.getCurrTime());
        repo.addNote(note);
    }
}
