package com.dokari4.umkmkkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dokari4.umkmkkn2.databinding.ActivityDetailBinding
import com.dokari4.umkmkkn2.utils.Utils

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Get value from Intent
        val umkmNamaPengusaha = intent.getStringExtra("umkmNamaPengusaha")
        val umkmAlamatUsaha = intent.getStringExtra("umkmAlamatUsaha")
        val umkmJenisUsaha = intent.getStringExtra("umkmJenisUsaha")
        val umkmKodeInput = intent.getStringExtra("umkmKodeInput")

        binding.btnBack.setOnClickListener {
            finish()
        }

        //bind image and text
        binding.imgPhoto.setImageResource(R.drawable.screenshot)
        binding.tvAddress.text = "Alamat\t\t: $umkmAlamatUsaha"
        binding.tvVariant.text = "Jenis\t\t\t: $umkmJenisUsaha"
        if (umkmKodeInput == "null") {
            binding.tvCode.text = "Kode Input Kosong"
        } else {
            binding.tvCode.text = umkmKodeInput
        }
        binding.tvName.text = umkmNamaPengusaha
        binding.fabPhone.setOnClickListener {
            Utils.showToast(this, "Message : $umkmAlamatUsaha")
        }
    }
}