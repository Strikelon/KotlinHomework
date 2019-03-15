package com.strikalov.kotlin2app.ui.note

import android.arch.lifecycle.ViewModel
import com.strikalov.kotlin2app.data.NotesRepository
import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.ui.base.BaseViewModel
import com.strikalov.kotlin2app.ui.model.NoteResult

class NoteViewModel(private val repository: NotesRepository = NotesRepository) : BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            repository.saveNote(it)
        }
    }

    fun loadNote(noteId: String) {
        repository.getNoteById(noteId).observeForever {result ->
            result ?: let { return@observeForever }

            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(result.data as? Note)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = result.error)
                }
            }
        }
    }

}