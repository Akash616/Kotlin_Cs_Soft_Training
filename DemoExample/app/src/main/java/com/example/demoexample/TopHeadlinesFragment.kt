package com.example.demoexample

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoexample.databinding.FragmentTopHeadlinesBinding

class TopHeadlinesFragment : Fragment(), NewsAdapter.NewsItemCLicked {

     var binding: FragmentTopHeadlinesBinding? = null
    lateinit var newsViewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTopHeadlinesBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiClient = RetrofitHelper.getInstance().create(APIClient::class.java)
        val newsRepository = NewsRepository(apiClient)
        newsViewModel = ViewModelProvider(this, NewsViewModelFactory(newsRepository, "in", "f70aa87b62f1414ea83b4b1226fed50f"))
            .get(NewsViewModel::class.java)

        binding!!.rvNews.layoutManager = LinearLayoutManager(context)
        binding!!.pBarNews.visibility = View.VISIBLE

        newsViewModel.topHeadline.observe(viewLifecycleOwner, Observer {
            if (it.articles.isNotEmpty()){
                binding!!.pBarNews.visibility = View.GONE
                val adapter = NewsAdapter(it.articles, this)
                binding!!.rvNews.adapter = adapter
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onItemClicked(item: Article) {
        val intent = CustomTabsIntent.Builder().build()
        intent.launchUrl(requireContext(), Uri.parse(item.url))
    }

}