package com.dev.restaurant

import com.dev.restaurant.models.Menu
import com.dev.restaurant.models.User

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {

    @POST("api/login_check")
    @FormUrlEncoded
    fun login(@Field ("_username") username:String,
              @Field("_password") password:String):Call<User>

    @GET("secretinfo")
    fun getSecret(@Header("Authorization") authToken: String): Call<ResponseBody>

    @GET("api/menus")
    fun getMenu():Call<ArrayList<Menu>>
}