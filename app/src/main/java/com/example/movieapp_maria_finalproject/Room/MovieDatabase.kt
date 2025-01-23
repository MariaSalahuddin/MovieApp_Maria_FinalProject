package com.example.movieapp_maria_finalproject.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao

    companion object{
        private var databaseInstance: MovieDatabase? = null


        fun getDBInstance(context: Context): MovieDatabase{
            var inst = databaseInstance
            synchronized(this) {
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        "moviesDB"
                    ).build()
                }
                databaseInstance = inst
            }
            return inst!!

        }
    }
}
