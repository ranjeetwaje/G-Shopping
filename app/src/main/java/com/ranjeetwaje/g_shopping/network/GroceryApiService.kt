package com.ranjeetwaje.g_shopping.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.ranjeetwaje.g_shopping.database.GroceryItem
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gshopping.s3.amazonaws.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface GroceryApiService {
    @GET("groceryItems1.json")
    fun getGroceryItems():
            Deferred<List<GroceryItem>>
}

object GroceryApi {
    val retrofitService : GroceryApiService by lazy {
        retrofit.create(GroceryApiService::class.java)
    }
}