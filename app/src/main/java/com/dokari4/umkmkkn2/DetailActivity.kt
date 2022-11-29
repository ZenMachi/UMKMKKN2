package com.dokari4.umkmkkn2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dokari4.umkmkkn2.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        //Get value from Intent
        val umkmName = intent.getStringExtra("umkmName")
        val phoneNumber = intent.getStringExtra("phoneNumber")

        //bind image and text
        binding.imgPhoto.setImageResource(R.drawable.screenshot)
        binding.tvName.text = umkmName
        binding.fabPhone.setOnClickListener {
            Utils.showToast(this, "Message : $phoneNumber")
        }
    }
}