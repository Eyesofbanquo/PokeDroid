package com.example.hilttut.model

import android.util.Log
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SpanishPerson @Inject constructor(
    @EnglishQualifier
    val englishPerson: Person
    ): Person {
    companion object {
        const val TAG = "SpanishPerson"
        const val SPEAK_SPANISH_MSG = "Despacito senor"
    }

    override fun speakLanguage() {
        Log.i(TAG, SPEAK_SPANISH_MSG)
    }
}