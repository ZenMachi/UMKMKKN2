package com.dokari4.umkmkkn2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dokari4.umkmkkn2.data.local.UmkmDatabase

class MyViewModelFactory constructor(private val umkmDatabase: UmkmDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(umkmDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}