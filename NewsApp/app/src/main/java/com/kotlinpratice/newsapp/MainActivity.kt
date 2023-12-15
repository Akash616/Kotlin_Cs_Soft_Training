package com.kotlinpratice.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinpratice.newsapp.adapter.NewsAdapter
import com.kotlinpratice.newsapp.modal.Article
import com.kotlinpratice.newsapp.modal.NewsModal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NewsAdapter.NewsItemCLicked {

    lateinit var rvNews: RecyclerView
    lateinit var apiInterface: ApiInterface
    lateinit var adapter: NewsAdapter
    lateinit var newsModal: NewsModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rvNews)

        rvNews.layoutManager = LinearLayoutManager(this)
        apiCall()
//        adapter = NewsAdapter(newsModal, this)
//        rvNews.adapter = adapter

    }

    private fun apiCall() {
        apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val call = apiInterface.getTopHeadlines("in", Constant.API_KEY)
        call.enqueue(object : Callback<NewsModal> {
            override fun onResponse(call: Call<NewsModal>, response: Response<NewsModal>) {
                if (response.isSuccessful){
                    newsModal = response.body()!!
                }
            }

            override fun onFailure(call: Call<NewsModal>, t: Throwable) {
                Log.d("onFailure: ", t.message.toString())
            }

        })
    }

    override fun onItemClicked(item: Article) {
        Toast.makeText(this, "CLicked", Toast.LENGTH_SHORT).show()
    }

}