package com.dokari4.umkmkkn2.home

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.umkmkkn2.DetailActivity
import com.dokari4.umkmkkn2.R
import com.dokari4.umkmkkn2.utils.Utils
import com.dokari4.umkmkkn2.ui.HomeAdapter
import com.dokari4.umkmkkn2.ui.MyViewModelFactory
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.databinding.ActivityMainBinding
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton

class HomeActivity : AppCompatActivity(), TextWatcher {
    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { HomeAdapter(
        context = this,
        listener = { value ->
            Intent(
                this,
                DetailActivity::class.java
            ).apply {
                putExtra("umkmNamaPengusaha", value.umkmNamaPengusaha)
                putExtra("umkmAlamatUsaha", value.umkmAlamatUsaha)
                putExtra("umkmJenisUsaha", value.umkmJenisUsaha)
                putExtra("umkmKodeInput", value.umkmKodeInput)
                putExtra("umkmNomorTelepon", value.umkmNomorTelepon)
                putExtra("umkmLokasiMaps", value.umkmLokasiMaps)
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
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        //bind recycler view to adapter
        binding.recyclerView.adapter = adapter

        //submitting data to the adapter which the maps it to recyclerview
        viewModel.getUmkm.observe(this, { result ->
            adapter.submitList(result)
        })

        binding.btnAdd.setOnClickListener {
            Utils.showToast(this, "SOON")
        }

        //Set Clickable on end icon TextInputLayout
        binding.inputLayout.setEndIconOnClickListener {
            searchName()
        }

        //enable search text listener
        binding.edSearch.addTextChangedListener(this)

        setToggleButton()
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
            viewModel.searchNamaPengusaha("%$query%").observe(this, { result ->
                adapter.submitList(result)
            })
        } else {
            viewModel.getUmkm.observe(this, { result ->
                adapter.submitList(result)
            })
        }
    }

    private fun setToggleButton() {
        binding.filterJenis.selectButton(R.id.btnAllJenis)
        binding.filterJenis.setOnSelectListener {
            when(it.id) {
                R.id.btnAllJenis -> {
                    viewModel.getUmkm.observe(this, { result ->
                        adapter.submitList(result)
                    })
                }

                R.id.btnKripik -> viewModel.searchJenisUsaha("%kripik%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnTempe -> viewModel.searchJenisUsaha("%tempe%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnJipang -> viewModel.searchJenisUsaha("%jipang%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnKembang -> viewModel.searchJenisUsaha("%kembang%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnKerupuk -> viewModel.searchJenisUsaha("%kerupuk%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnRambak -> viewModel.searchJenisUsaha("%rambak%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnRengginang -> viewModel.searchJenisUsaha("%rengginang%").observe(this, { result ->
                    adapter.submitList(result)
                })

                R.id.btnSlondok -> viewModel.searchJenisUsaha("%slondok%").observe(this, { result ->
                    adapter.submitList(result)
                })

                else -> return@setOnSelectListener
            }
        }
    }
}
