package com.example.hilttut.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hilttut.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}