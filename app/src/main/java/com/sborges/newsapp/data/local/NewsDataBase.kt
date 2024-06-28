package com.sborges.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sborges.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract val newsDao: NewsDao
}