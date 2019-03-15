package com.strikalov.kotlin2app.ui.model

sealed class NoteResult {
    data class Success<out T>(val data: T): NoteResult()
    data class Error(val error: Throwable?): NoteResult()
}