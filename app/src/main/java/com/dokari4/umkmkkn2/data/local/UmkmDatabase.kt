package com.dokari4.umkmkkn2.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dokari4.umkmkkn2.data.StartingData

@Database(entities = [UmkmEntity::class], version = 1)
abstract class UmkmDatabase : RoomDatabase() {
    abstract val dao: UmkmDao

    companion object{
        @Volatile
        private var instance:UmkmDatabase? = null

        fun getInstance(context: Context):UmkmDatabase?{
            if (instance == null){
                synchronized(UmkmDatabase::class.java){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UmkmDatabase::class.java,
                        "notes"
                    )
                        .addCallback(StartingData(context))
                        .build()
                }
            }
            return instance
        }
    }
}