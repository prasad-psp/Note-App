package com.psp.note_app.repo;

import android.content.Context;

import androidx.paging.PagingSource;

import com.psp.note_app.model.Note;
import com.psp.note_app.room.NoteDatabase;

public class HomeFragRepo {

    private final Context context;

    public HomeFragRepo(Context context) {
        this.context = context;
    }

    public PagingSource<Integer, Note> getNotes() {
        return NoteDatabase.getInstance(context).noteDao().getNotes();
    }

    public void removeNote(int id) {
        new Thread(() -> NoteDatabase.getInstance(context).noteDao().deleteNote(id)).start();
    }
}
