package com.dokari4.umkmkkn2.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.umkmkkn2.DetailActivity
import com.dokari4.umkmkkn2.Utils
import com.dokari4.umkmkkn2.ui.HomeAdapter
import com.dokari4.umkmkkn2.ui.MyViewModelFactory
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { HomeAdapter(
        context = this,
        listener = { value ->
            Intent(
                this,
                DetailActivity::class.java
            ).apply {
                putExtra("umkmName", value.umkmName)
                putExtra("phoneNumber", value.umkmPhoneNumber)
                startActivity(this)
                }
        }
    )}
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //init binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //set actionbar to be hidden
        supportActionBar?.hide()

        //instance of database
        val umkmDatabase = UmkmDatabase.getInstance(this)
        val myViewModelFactory = MyViewModelFactory(umkmDatabase!!)

        //ini viewModel
        viewModel = ViewModelProvider(this, myViewModelFactory).get(HomeViewModel::class.java)

        // set layout manager
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        //bind recycler view to adapter
        binding.recyclerView.adapter = adapter

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.getUmkm.observe(this, { result ->
            adapter.submitList(result)
        })

        //Set Clickable on end icon TextInputLayout
        binding.inputLayout.setEndIconOnClickListener {
            searchName()
        }

        //enable search text listener
        binding.edSearch.addTextChangedListener(this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        searchName()
    }

    override fun afterTextChanged(p0: Editable?) {

    }

    private fun searchName() {
        val query = binding.edSearch.text.toString().trim()
        if (query.isNotEmpty()) {
            viewModel.searchUmkm("%$query%").observe(this, { result ->
                adapter.submitList(result)
            })
        } else {
            viewModel.getUmkm.observe(this, { result ->
                adapter.submitList(result)
            })
        }
        // For Test Purpose
        Utils.showToast(this, query)
    }
}
