package com.example.hilttut

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class InfoView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAtr: Int = 0) :
     LinearLayout(context, attrs, defStyleAtr) {

    lateinit var titleText: TextView
    lateinit var subtitleText: TextView

    init {
        inflate(context, R.layout.info_view, this)
        titleText = findViewById(R.id.title)
        subtitleText = findViewById(R.id.subtitle)
    }
}