package com.kemalakkus.newsapp_retrofit_room_mvvm.api

import com.kemalakkus.newsapp_retrofit_room_mvvm.model.NewsResponse
import com.kemalakkus.newsapp_retrofit_room_mvvm.util.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode:String="tr",
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey:String=API_KEY
    ): Response<NewsResponse>
    @GET("v2/top-headlines")
    suspend fun searchNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber: Int,
        @Query("apiKey")
        apiKey:String=API_KEY
    ):Response<NewsResponse>

}