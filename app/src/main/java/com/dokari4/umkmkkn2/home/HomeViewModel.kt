package com.dokari4.umkmkkn2.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.data.local.UmkmEntity
import kotlinx.coroutines.launch

class HomeViewModel(private val umkmDatabase: UmkmDatabase) : ViewModel() {
    val getUmkm = umkmDatabase.dao.getAllUmkm()

    fun searchUmkm(umkm: String): LiveData<MutableList<UmkmEntity>> {
       return umkmDatabase.dao.searchUmkm(umkm)
    }
}