package com.kotlinpratice.socialmediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.kotlinpratice.socialmediaapp.daos.PostDao

class CreatePostActivity : AppCompatActivity() {

    lateinit var etPostInput: EditText
    lateinit var btnPostInput: Button
    private lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        btnPostInput = findViewById(R.id.btnPostInput)
        etPostInput = findViewById(R.id.etPostInput)

        postDao = PostDao()

        btnPostInput.setOnClickListener {
            val input = etPostInput.text.toString().trim()
            if (input.isNotEmpty()){
                postDao.addPost(input)
                finish()
            }
        }

    }
}