package com.example.taskhumanassignment.domain.models

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface NetworkRequestAPI {

    @GET("v0.8/discover/topicDetails/physical%20fitness")
    fun getSkills(
        @Header(RequestHeaders.ACCEPT) accept: String,
        @Header(RequestHeaders.AUTHORIZATION) Authorization: String
    ) : Call<ResponseBody>


    @POST("v0.8/favorite/add")
    fun addSkill(
        @Header(RequestHeaders.ACCEPT) accept: String,
        @Header(RequestHeaders.AUTHORIZATION) Authorization: String,
        @Header(RequestHeaders.CONTENT_TYPE) contentType: String,
        @Body requestBody: RequestBody
    ) : Call<ResponseBody>

    @POST("v0.8/favorite/remove")
    fun removeSkill(
        @Header(RequestHeaders.ACCEPT) accept: String,
        @Header(RequestHeaders.AUTHORIZATION) Authorization: String,
        @Header(RequestHeaders.CONTENT_TYPE) contentType: String,
        @Body requestBody: RequestBody
    ) : Call<ResponseBody>

}