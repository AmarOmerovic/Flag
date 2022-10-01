package com.amaromerovic.flag

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.flag.databinding.ActivityResultBinding
import com.amaromerovic.flag.util.Util

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.name.text = intent.getStringExtra(Util.NAME_KEY)
        binding.score.text = String.format(
            "Your Score: ${
                intent.getIntExtra(Util.CORRECT_KEY, 0).toString().trim()
            }/10"
        )


        binding.finishButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}