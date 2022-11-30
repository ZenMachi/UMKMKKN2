package com.dokari4.umkmkkn2.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UmkmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUmkm(umkmEntity: UmkmEntity)

    @Query("SELECT * FROM Umkm_table ORDER BY id ASC")
    fun getAllUmkm(): LiveData<MutableList<UmkmEntity>>

    @Query("SELECT * FROM Umkm_table WHERE umkmNamaPengusaha like :umkmNamaPengusaha")
    fun searchUmkm(umkmNamaPengusaha: String): LiveData<MutableList<UmkmEntity>>
}