package com.strikalov.kotlin2app.ui.note

import android.arch.lifecycle.ViewModel
import com.strikalov.kotlin2app.data.NotesRepository
import com.strikalov.kotlin2app.data.entity.Note

class NoteViewModel(private val repository: NotesRepository = NotesRepository) : ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note){
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            repository.saveNote(it)
        }
    }

}