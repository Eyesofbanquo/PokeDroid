package com.example.hilttut

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.hilttut.activities.ListActivity
import com.example.hilttut.model.SpanishPerson

class MainFragment: Fragment() {
    // region Properties
    private lateinit var launchNextButton: Button
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_next_button_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchNextButton = view.findViewById(R.id.launchNextActivity)
        launchNextButton.setOnClickListener {
            val intent = Intent(context, ListActivity::class.java)
            startActivity(intent)
        }
    }
}