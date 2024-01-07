package com.example.demoexample

data class TopHeadlinesModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)