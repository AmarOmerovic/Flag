package com.amaromerovic.flag

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.amaromerovic.flag.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstAnswer.setOnClickListener { markSelected(binding.firstAnswer) }
        binding.secondAnswer.setOnClickListener { markSelected(binding.secondAnswer) }
        binding.thirdAnswer.setOnClickListener { markSelected(binding.thirdAnswer) }
        binding.fourthAnswer.setOnClickListener { markSelected(binding.fourthAnswer) }


        binding.submitButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }


    private fun markSelected(view: View) {
        if (view == binding.firstAnswer) {
            binding.firstAnswer.setStrokeColorResource(R.color.pink)
            binding.firstAnswer.strokeWidth = 5
        } else {
            binding.firstAnswer.setStrokeColorResource(R.color.black)
            binding.firstAnswer.strokeWidth = 2
        }

        if (view == binding.secondAnswer) {
            binding.secondAnswer.setStrokeColorResource(R.color.pink)
            binding.secondAnswer.strokeWidth = 5
        } else {
            binding.secondAnswer.setStrokeColorResource(R.color.black)
            binding.secondAnswer.strokeWidth = 2
        }

        if (view == binding.thirdAnswer) {
            binding.thirdAnswer.setStrokeColorResource(R.color.pink)
            binding.thirdAnswer.strokeWidth = 5
        } else {
            binding.thirdAnswer.setStrokeColorResource(R.color.black)
            binding.thirdAnswer.strokeWidth = 2
        }

        if (view == binding.fourthAnswer) {
            binding.fourthAnswer.setStrokeColorResource(R.color.pink)
            binding.fourthAnswer.strokeWidth = 5
        } else {
            binding.fourthAnswer.setStrokeColorResource(R.color.black)
            binding.fourthAnswer.strokeWidth = 2
        }

    }
}