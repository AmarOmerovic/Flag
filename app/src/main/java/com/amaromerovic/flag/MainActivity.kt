package com.amaromerovic.flag

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.flag.databinding.ActivityMainBinding
import com.amaromerovic.flag.util.Util

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.startButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.nameText.text)) {
                binding.textInputLayout.error = "Field is empty!"
            } else if (binding.nameText.text.toString().trim().length < 3) {
                binding.textInputLayout.error = "Oops, too short for a name!"
            } else {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra(Util.NAME_KEY, binding.nameText.text.toString().trim())
                startActivity(intent)
                finish()
            }
        }
    }
}