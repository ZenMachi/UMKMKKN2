package com.dokari4.umkmkkn2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UmkmDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUmkm(umkmEntity: UmkmEntity)

    @Query("SELECT * FROM Umkm_table ORDER BY umkmNamaPengusaha ASC")
    fun getAllUmkm(): LiveData<MutableList<UmkmEntity>>

    @Query("SELECT * FROM Umkm_table WHERE umkmNamaPengusaha like :umkmNamaPengusaha")
    fun searchNamaPengusaha(umkmNamaPengusaha: String): LiveData<MutableList<UmkmEntity>>

    @Query("select * from Umkm_table where umkmJenisUsaha like :umkmJenisUsaha")
    fun searchJenisUsaha(umkmJenisUsaha: String): LiveData<MutableList<UmkmEntity>>

    @Update
    suspend fun updateUmkm(umkmEntity: UmkmEntity)
}