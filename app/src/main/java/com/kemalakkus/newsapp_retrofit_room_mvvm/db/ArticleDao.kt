package com.kemalakkus.newsapp_retrofit_room_mvvm.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.kemalakkus.newsapp_retrofit_room_mvvm.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Upsert (article: Article):Long
    @Query("SELECT*FROM article")
    fun getAllArticle(): LiveData<List<Article>>
    @Delete
    suspend fun deleteArticles(article: Article)


}