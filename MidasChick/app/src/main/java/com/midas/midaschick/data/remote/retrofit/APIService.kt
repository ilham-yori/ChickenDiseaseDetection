package com.midas.midaschick.data.remote.retrofit

import com.midas.midaschick.data.remote.response.ServerResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface APIService {

    @Multipart
    @POST("predictAPI")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("select_model") select_model: RequestBody,
        @Part("api_key") api_key: RequestBody
    ): Call<ServerResponse>
}