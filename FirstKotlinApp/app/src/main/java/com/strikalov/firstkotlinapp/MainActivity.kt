package com.strikalov.firstkotlinapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        button = findViewById(R.id.button_one)

        val buttonPressedString = getString(R.string.text_button_pressed)

        button.setOnClickListener {
            textView.setText(buttonPressedString)
        }

    }
}
