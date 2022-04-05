package com.psp.note_app.repo;

import android.content.Context;

import com.psp.note_app.model.Note;
import com.psp.note_app.room.NoteDatabase;

public class NewNoteFragRepo {

    private final Context context;

    public NewNoteFragRepo(Context context) {
        this.context = context;
    }

    public void addNote(Note note) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NoteDatabase.getInstance(context).noteDao().insert(note);
            }
        }).start();
    }
}
