package com.kotlinpratice.newsapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
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
    lateinit var pBarNews: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rvNews)
        pBarNews = findViewById(R.id.pBarNews)

        rvNews.layoutManager = LinearLayoutManager(this)

        apiCall()

    }

    private fun apiCall() {
        pBarNews.visibility = View.VISIBLE
        apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        val call = apiInterface.getTopHeadlines("in", Constant.API_KEY)
        call.enqueue(object : Callback<NewsModal> {
            override fun onResponse(call: Call<NewsModal>, response: Response<NewsModal>) {
                if (response.isSuccessful){
                    newsModal = response.body()!!
                    pBarNews.visibility = View.GONE
                    callAdapter()
                }
            }

            override fun onFailure(call: Call<NewsModal>, t: Throwable) {
                Log.d("onFailure: ", t.message.toString())
            }

        })
    }

    private fun callAdapter() {
        adapter = NewsAdapter(newsModal, this)
        rvNews.adapter = adapter
    }

    override fun onItemClicked(item: Article) {
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(this@MainActivity, Uri.parse(item.url))

    }

}