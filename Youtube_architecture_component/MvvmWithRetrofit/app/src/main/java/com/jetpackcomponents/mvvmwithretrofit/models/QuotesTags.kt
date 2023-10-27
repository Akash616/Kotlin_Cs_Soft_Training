package com.jetpackcomponents.mvvmwithretrofit.models

data class QuotesTags(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultXX>,
    val totalCount: Int,
    val totalPages: Int
)