package com.strikalov.kotlin2app.ui.note

import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.ui.base.BaseViewState

class NoteViewState(note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)