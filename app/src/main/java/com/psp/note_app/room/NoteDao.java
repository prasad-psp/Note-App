package com.psp.note_app.room;

import static com.psp.note_app.utils.Constants.TABLE_NAME;

import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.psp.note_app.model.Note;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);

    @Query("SELECT * FROM "+TABLE_NAME+" ORDER BY id DESC")
    PagingSource<Integer, Note> getNotes();
}
