package com.dokari4.umkmkkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.databinding.ActivityEditBinding
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private val umkmDatabase by lazy { UmkmDatabase.getInstance(this)?.dao }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val idUmkm = intent.getLongExtra("idUmkm", 0)
        val umkmNamaPengusaha = intent.getStringExtra("umkmNamaPengusaha")
        val umkmNamaUsaha = intent.getStringExtra("umkmNamaUsaha")
        val umkmAlamatUsaha = intent.getStringExtra("umkmAlamatUsaha")
        val umkmJenisUsaha = intent.getStringExtra("umkmJenisUsaha")
        val umkmKodeInput = intent.getStringExtra("umkmKodeInput")
        val umkmNomorTelepon = intent.getStringExtra("umkmNomorTelepon")
        val umkmLokasiMaps = intent.getStringExtra("umkmLokasiMaps")
        val umkmFotoUsaha = intent.getStringExtra("umkmFotoUsaha")


        binding.edNamaPengusaha.setText(umkmNamaPengusaha)
        binding.edAlamatUsaha.setText(umkmAlamatUsaha)
        binding.edJenisUsaha.setText(umkmJenisUsaha)
        binding.edKodeInput.setText(umkmKodeInput)
        binding.edNomorTelepon.setText(umkmNomorTelepon)
        binding.edLokasiMaps.setText(umkmLokasiMaps)
        binding.edFotoUsaha.setText(umkmFotoUsaha)
        binding.edNamaUsaha.setText(umkmNamaUsaha)

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                umkmDatabase?.updateUmkm(
                    com.dokari4.umkmkkn2.data.local.UmkmEntity(
                        umkmNamaPengusaha = binding.edNamaPengusaha.text.toString().trim(),
                        umkmAlamatUsaha = binding.edAlamatUsaha.text.toString().trim(),
                        umkmJenisUsaha = binding.edJenisUsaha.text.toString().trim(),
                        umkmKodeInput = binding.edKodeInput.text.toString().trim(),
                        umkmNomorTelepon = binding.edNomorTelepon.text.toString().trim(),
                        umkmLokasiMaps = binding.edLokasiMaps.text.toString().trim(),
                        umkmFotoUsaha = binding.edFotoUsaha.text.toString().trim(),
                        umkmNamaUsaha = binding.edNamaUsaha.text.toString().trim(),
                        id = idUmkm
                    )
                )
            }
            finish()
        }

    }
}