package com.kotlinpratice.newsapp.modal

data class NewsModal(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)