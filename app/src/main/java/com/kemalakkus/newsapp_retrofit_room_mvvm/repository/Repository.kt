package com.kemalakkus.newsapp_retrofit_room_mvvm.repository

import com.kemalakkus.newsapp_retrofit_room_mvvm.db.ArticleDao
import com.kemalakkus.newsapp_retrofit_room_mvvm.model.Article

class Repository(private  val dao : ArticleDao){

    suspend fun saveArticles(article: Article)=dao.Upsert(article)
    suspend fun deleteArticles(article: Article)=dao.deleteArticles(article)
    fun getSaveArticles()=dao.getAllArticle()
}