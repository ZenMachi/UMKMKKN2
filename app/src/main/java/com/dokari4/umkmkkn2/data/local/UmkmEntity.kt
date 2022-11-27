package com.dokari4.umkmkkn2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Umkm_table")
data class UmkmEntity (
    val umkmName: String,

    val umkmPhoneNumber: String,

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    )