package com.kotlinpratice.roomwordexample

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kotlinpratice.roomwordexample.adapter.WordListAdapter
import com.kotlinpratice.roomwordexample.room.entity.Word
import com.kotlinpratice.roomwordexample.viewmodel.WordViewModel
import com.kotlinpratice.roomwordexample.viewmodel.WordViewModelFactory

//https://developer.android.com/codelabs/android-room-with-a-view-kotlin#7
//Room is a database layer on top of an SQLite database.

class MainActivity : AppCompatActivity() {

    lateinit var rvWord: RecyclerView
    private lateinit var wordViewModel: WordViewModel
    private val newWordActivityRequestCode = 1
    lateinit var fabWord: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordViewModel = ViewModelProvider(this, WordViewModelFactory((application as WordsApplication).repository))
            .get(WordViewModel::class.java)

        rvWord = findViewById(R.id.rvWord)
        rvWord.layoutManager = LinearLayoutManager(this)
        val adapter = WordListAdapter()
        rvWord.adapter = adapter

        wordViewModel.allWords.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        fabWord = findViewById(R.id.fabWord)
        fabWord.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra("EXTRA_REPLY")?.let {
                val word = Word(it)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

    }


}