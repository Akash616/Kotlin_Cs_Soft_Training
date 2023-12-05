package com.imageuploadretrofit.uploadimageexample

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadService {

    //upload images with the help of retrofit we use Multipart.
    //Multipart - apki request ka andar multiple part honga. Ak part ka anadr images ho sakti hai,
    //dusra part mai file ho sakti hai, 3rd part mai form ka data ho sakta hai.......

    @Multipart
    @POST("/single")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): ImageResponse

}