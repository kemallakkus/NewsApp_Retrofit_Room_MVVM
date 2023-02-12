package com.kemalakkus.newsapp_retrofit_room_mvvm.api

import com.kemalakkus.newsapp_retrofit_room_mvvm.model.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServive {
    companion object {
        private val BASE_URL = "https://newsapi.org/"

        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsAPI::class.java)


        suspend fun getBreakingNews(counryCode: String, pageNumber: Int): Response<NewsResponse> {
            return api.getBreakingNews(counryCode, pageNumber)
        }

        suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse> {
            return api.searchNews(searchQuery, pageNumber)
        }
    }

}