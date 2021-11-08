package com.nechaev.mycard.data.network.user

import com.google.gson.GsonBuilder
import com.nechaev.mycard.data.model.deserializers.UserListDeserializer
import com.nechaev.mycard.data.model.user.UserList
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitUser {

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private const val baseUrl = "https://hr.peterpartner.net/test/android/v1/"

    private val retrofitUsersBuider = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(buildGsonConverterFactory()).build()

    private fun buildGsonConverterFactory(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(UserList::class.java, UserListDeserializer())
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    fun getRetrofitUsers(): Retrofit {
        return retrofitUsersBuider
    }
}