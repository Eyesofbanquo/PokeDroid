package com.example.hilttut.model

import android.util.Log
import javax.inject.Inject

class EnglishPerson @Inject constructor(): Person {

    companion object {
        const val TAG = "EnglishPerson"
        const val SPEAK_ENGLISH_MSG = "Hello kind sir."
    }

    override fun speakLanguage() {
        Log.i(TAG, SPEAK_ENGLISH_MSG)
    }
}