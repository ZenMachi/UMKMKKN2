package com.dokari4.umkmkkn2

import android.content.Intent
import android.net.Uri
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
        val umkmNomorTelepon = intent.getStringExtra("umkmNomorTelepon")
        val umkmLokasiMaps = intent.getStringExtra("umkmLokasiMaps")

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
            if (umkmNomorTelepon == "null") {
                Utils.showToast(this, "Nomor HP Belum Ditambahkan")
            } else {
                val url = Uri.parse("https://api.whatsapp.com/send/?phone=$umkmNomorTelepon&text=Halo+saya+ingin+memesan")
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }
        }
        binding.btnMaps.setOnClickListener {
            if (umkmLokasiMaps == "null") {
                Utils.showToast(this, "Lokasi Maps Belum Terdaftar")
            } else {
                val url = Uri.parse(umkmLokasiMaps)
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }
        }
    }
}