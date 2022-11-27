package com.dokari4.umkmkkn2

import androidx.lifecycle.ViewModel
import com.dokari4.umkmkkn2.data.local.UmkmDatabase

class HomeViewModel(private val umkmDatabase: UmkmDatabase) : ViewModel() {
    val umkm = umkmDatabase.dao.getAllUmkm()
}