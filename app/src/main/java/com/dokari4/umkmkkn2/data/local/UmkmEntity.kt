package com.dokari4.umkmkkn2.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Umkm_table")
data class UmkmEntity(
    val umkmNamaPengusaha: String,

    val umkmAlamatUsaha: String,

    val umkmJenisUsaha: String,

    val umkmKodeInput: String? = null,

    val umkmNomorTelepon: String? = null,

    val umkmLokasiMaps: String? = null,

    val umkmFotoUsaha: String? = null,

    val umkmNamaUsaha: String? = null,

//    val umkmFotoGaleri: Array<String>,

    @PrimaryKey(autoGenerate = true)
    var id : Long = 0,
    )