package com.kotlinpratice.roomwordexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewWordActivity : AppCompatActivity() {

    lateinit var etWord: EditText
    lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_word)

        etWord = findViewById(R.id.etWord)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(etWord.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = etWord.text.toString()
                replyIntent.putExtra("EXTRA_REPLY", word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
}