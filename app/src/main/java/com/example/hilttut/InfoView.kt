package com.example.hilttut

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout

class InfoView @JvmOverloads constructor(context: Context,
                                         attrs: AttributeSet? = null,
                                         defStyleAtr: Int = 0) :
     LinearLayout(context, attrs, defStyleAtr) {

    init {
        inflate(context, R.layout.info_view, this)
    }
}