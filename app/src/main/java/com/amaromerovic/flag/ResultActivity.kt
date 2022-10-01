package com.amaromerovic.flag

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.flag.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.finishButton.setOnClickListener {
            finish()
        }
    }
}