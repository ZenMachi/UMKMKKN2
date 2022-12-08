package com.dokari4.umkmkkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.dokari4.umkmkkn2.data.local.UmkmDatabase
import com.dokari4.umkmkkn2.databinding.ActivityAddBinding
import com.dokari4.umkmkkn2.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.dokari4.umkmkkn2.data.local.UmkmEntity as UmkmEntity1

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val umkmDatabase by lazy { UmkmDatabase.getInstance(this)?.dao }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            finish()
        }

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch {
                umkmDatabase?.insertUmkm(
                    com.dokari4.umkmkkn2.data.local.UmkmEntity(
                        binding.edNamaPengusaha.text.toString().trim(),
                        binding.edAlamatUsaha.text.toString().trim(),
                        binding.edJenisUsaha.text.toString().trim(),
                        binding.edKodeInput.text.toString().trim(),
                        binding.edNomorTelepon.text.toString().trim(),
                        binding.edLokasiMaps.text.toString().trim(),
                        binding.edFotoUsaha.text.toString().trim(),
                        binding.edNamaUsaha.text.toString().trim(),
                    )
                )
            }
            finish()
        }

    }
}