package com.dokari4.umkmkkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { HomeAdapter(this,
        listener = {
            Toast.makeText(this, "Yay u clicked ${it.umkmName} Desc: ${it.umkmPhoneNumber}", Toast.LENGTH_SHORT).show()
    }) }
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //instance of database
        val umkmDatabase = UmkmDatabase.getInstance(this)
        val myViewModelFactory = MyViewModelFactory(umkmDatabase!!)

        viewModel = ViewModelProvider(this, myViewModelFactory).get(HomeViewModel::class.java)

        // set layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.umkm.observe(this, Observer { result ->
            adapter.submitList(result)
            binding.recyclerView.adapter = adapter
        })
    }
}