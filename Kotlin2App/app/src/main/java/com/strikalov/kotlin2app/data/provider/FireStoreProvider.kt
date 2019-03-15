package com.strikalov.kotlin2app.data.provider

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.ui.model.NoteResult
import com.github.ajalt.timberkt.Timber
import com.google.firebase.firestore.QueryDocumentSnapshot

class FireStoreProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

    private val store by lazy {
        FirebaseFirestore.getInstance()
    }

    private val notesReference by lazy {
        store.collection(NOTES_COLLECTION)
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(note.id)
            .set(note)
            .addOnSuccessListener {
                Timber.d {"Note $note is saved"}
                result.value = NoteResult.Success(note)
            }.addOnFailureListener {
                Timber.e(it) {"Error saving note $note"}
                result.value = NoteResult.Error(it)
            }

        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.document(id)
            .get()
            .addOnSuccessListener { snapshot ->
                val note = snapshot.toObject(Note::class.java)
                result.value = NoteResult.Success(note)
            }.addOnFailureListener {
                Timber.e(it) {"Error reading note with id $id"}
                result.value = NoteResult.Error(it)
            }

        return result
    }

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        notesReference.addSnapshotListener { snapshot, e ->
            e?.let {
                result.value = NoteResult.Error(e)
            } ?: let {
                snapshot?.let {
                    val notes = mutableListOf<Note>()
                    for(doc : QueryDocumentSnapshot in snapshot) {
                        val note = doc.toObject(Note::class.java)
                        notes.add(note)
                    }
                    result.value = NoteResult.Success(notes)
                }
            }
        }
        return result
    }

}