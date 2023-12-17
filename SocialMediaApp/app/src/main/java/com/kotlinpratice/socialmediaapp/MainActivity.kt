package com.kotlinpratice.socialmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.Query
import com.kotlinpratice.socialmediaapp.adapter.IPostAdapter
import com.kotlinpratice.socialmediaapp.adapter.RvPostAdapter
import com.kotlinpratice.socialmediaapp.daos.PostDao
import com.kotlinpratice.socialmediaapp.models.Post

class MainActivity : AppCompatActivity(), IPostAdapter {

    lateinit var fab: FloatingActionButton
    private lateinit var adapter: RvPostAdapter
    lateinit var recyclerview: RecyclerView
    lateinit var postDao: PostDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recyclerview)

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }

        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {

        postDao = PostDao()
        val postCollection = postDao.postCollections
        val query = postCollection.orderBy("createdAt", Query.Direction.DESCENDING)
        val recyclerviewOption = FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()

        adapter = RvPostAdapter(recyclerviewOption, this)

        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = adapter

    }

    override fun onStart() { //App start
        super.onStart()
        adapter.startListening() //automatically update and notify recyclerview
    }

    override fun onStop() { //App Stop
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes(postId)
    }

}