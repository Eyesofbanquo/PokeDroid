package com.example.hilttut.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.hilttut.R
import com.example.hilttut.model.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @SpanishQualiifier
    @Inject
    lateinit var spanishMan: Person

    @Inject
    lateinit var animalCreator: AnimalUtilFactory
    private lateinit var animal: Animal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spanishMan.speakLanguage()
        animal = animalCreator.create("Sparky", 24)


    }
}