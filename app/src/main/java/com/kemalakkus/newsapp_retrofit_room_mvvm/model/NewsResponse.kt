package com.kemalakkus.newsapp_retrofit_room_mvvm.model

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)