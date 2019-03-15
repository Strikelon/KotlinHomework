package com.strikalov.kotlin2app.data

import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.data.provider.FireStoreProvider
import com.strikalov.kotlin2app.data.provider.RemoteDataProvider

object NotesRepository {

    private val remoteDataProvider: RemoteDataProvider = FireStoreProvider()

    fun getNoteById(id: String) = remoteDataProvider.getNoteById(id)
    fun saveNote(note: Note) = remoteDataProvider.saveNote(note)
    fun getNotes() = remoteDataProvider.subscribeToAllNotes()

}