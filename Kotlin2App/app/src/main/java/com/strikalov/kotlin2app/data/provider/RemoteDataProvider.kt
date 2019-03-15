package com.strikalov.kotlin2app.data.provider

import android.arch.lifecycle.LiveData
import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.ui.model.NoteResult

interface RemoteDataProvider {

    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun subscribeToAllNotes(): LiveData<NoteResult>

}