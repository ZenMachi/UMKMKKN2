package com.dokari4.umkmkkn2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
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
        val idUmkm = intent.getLongExtra("idUmkm", 0)
        val umkmNamaPengusaha = intent.getStringExtra("umkmNamaPengusaha")
        val umkmNamaUsaha = intent.getStringExtra("umkmNamaUsaha")
        val umkmAlamatUsaha = intent.getStringExtra("umkmAlamatUsaha")
        val umkmJenisUsaha = intent.getStringExtra("umkmJenisUsaha")
        val umkmKodeInput = intent.getStringExtra("umkmKodeInput")
        val umkmNomorTelepon = intent.getStringExtra("umkmNomorTelepon")
        val umkmLokasiMaps = intent.getStringExtra("umkmLokasiMaps")
        val umkmFotoUsaha = intent.getStringExtra("umkmFotoUsaha")

        binding.btnBack.setOnClickListener {
            finish()
        }

        //bind image and text
        if (umkmFotoUsaha == "null") {
            binding.imgPhoto.setImageResource(R.drawable.placeholder)
        } else {
            Glide
                .with(this)
                .load(umkmFotoUsaha)
                .into(binding.imgPhoto)
        }
        binding.tvAddress.text = "Alamat\t\t: $umkmAlamatUsaha"
        binding.tvVariant.text = "Jenis\t\t\t: $umkmJenisUsaha"
        if (umkmNamaUsaha == "null" || umkmNamaUsaha == null || umkmNamaUsaha.isEmpty()) {
            binding.tvNameUsaha.text = "Nama Usaha Belum Diisi"
        } else {
            binding.tvNameUsaha.text = "Nama Usaha\t: $umkmNamaUsaha"
        }
        if (umkmKodeInput == "null" || umkmKodeInput == null || umkmKodeInput.isEmpty()) {
            binding.tvCode.text = "Kode Input Kosong"
        } else {
            binding.tvCode.text = umkmKodeInput
        }
        binding.tvName.text = umkmNamaPengusaha
        binding.fabPhone.setOnClickListener {
            if (umkmNomorTelepon == "null" || umkmNomorTelepon == null || umkmNomorTelepon.isEmpty()) {
                Utils.showToast(this, "Nomor HP Belum Ditambahkan")
            } else {
                val url = Uri.parse("https://api.whatsapp.com/send/?phone=$umkmNomorTelepon&text=Halo+saya+ingin+memesan")
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }
        }
        binding.btnMaps.setOnClickListener {
            if (umkmLokasiMaps == "null" || umkmLokasiMaps == null || umkmLokasiMaps.isEmpty()) {
                Utils.showToast(this, "Lokasi Maps Belum Terdaftar")
            } else {
                val url = Uri.parse(umkmLokasiMaps)
                val intent = Intent(Intent.ACTION_VIEW, url)
                startActivity(intent)
            }
        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java).apply {
                putExtra("idUmkm", idUmkm)
                putExtra("umkmNamaPengusaha", umkmNamaPengusaha)
                putExtra("umkmNamaUsaha", umkmNamaUsaha)
                putExtra("umkmAlamatUsaha", umkmAlamatUsaha)
                putExtra("umkmJenisUsaha", umkmJenisUsaha)
                putExtra("umkmKodeInput", umkmKodeInput)
                putExtra("umkmNomorTelepon", umkmNomorTelepon)
                putExtra("umkmLokasiMaps", umkmLokasiMaps)
                putExtra("umkmFotoUsaha", umkmFotoUsaha)
            }
            finish()
            startActivity(intent)
        }
    }
}