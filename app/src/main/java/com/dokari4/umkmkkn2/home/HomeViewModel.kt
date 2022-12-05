package com.dokari4.umkmkkn2.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.data.local.UmkmEntity

class HomeViewModel(private val umkmDatabase: UmkmDatabase) : ViewModel() {
    val getUmkm = umkmDatabase.dao.getAllUmkm()

    fun searchNamaPengusaha(namaPengusaha: String): LiveData<MutableList<UmkmEntity>> {
       return umkmDatabase.dao.searchNamaPengusaha(namaPengusaha)
    }

    fun searchJenisUsaha(jenisUsaha: String): LiveData<MutableList<UmkmEntity>> {
        return umkmDatabase.dao.searchJenisUsaha(jenisUsaha)
    }
}