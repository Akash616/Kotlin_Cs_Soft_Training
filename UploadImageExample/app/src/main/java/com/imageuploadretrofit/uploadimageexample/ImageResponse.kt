package com.imageuploadretrofit.uploadimageexample

data class ImageResponse(
    val destination: String,
    val encoding: String,
    val fieldname: String,
    val filename: String,
    val mimetype: String,
    val originalname: String,
    val path: String,
    val size: Int
)
