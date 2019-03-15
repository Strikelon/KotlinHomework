package com.strikalov.kotlin2app.ui.main

import com.strikalov.kotlin2app.data.entity.Note
import com.strikalov.kotlin2app.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) : BaseViewState<List<Note>?>(notes, error)