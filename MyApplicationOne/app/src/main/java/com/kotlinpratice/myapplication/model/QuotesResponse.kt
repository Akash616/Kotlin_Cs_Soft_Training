package com.kotlinpratice.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QuotesResponse(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    @SerializedName("results") val results: List<QuotesResult>,
    val totalCount: Int,
    val totalPages: Int
)