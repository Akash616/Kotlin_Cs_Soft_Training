package com.jetpackcomponents.mvvmwithretrofit.models

data class AuthorsList(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<ResultX>,
    val totalCount: Int,
    val totalPages: Int
)