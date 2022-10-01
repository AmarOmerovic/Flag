package com.amaromerovic.flag

import android.content.Context
import android.content.Intent
import android.os.*
import android.text.TextUtils
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.amaromerovic.flag.databinding.ActivityQuestionBinding
import com.amaromerovic.flag.model.Country
import com.amaromerovic.flag.util.Util
import com.google.android.material.button.MaterialButton


class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding
    private val countries = Util.getCountries()
    private lateinit var country: Country
    private var questionCount = 1
    private lateinit var answer: MaterialButton
    private var userChoice: MaterialButton? = null
    private var correct = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(Util.NAME_KEY)
        val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        val vibrator = vibratorManager.defaultVibrator
        generateQuestion()

        binding.submitButton.setOnClickListener {
            binding.submitButton.isEnabled = false
            if (questionCount <= 11) {
                unselect()
                if (answer == userChoice) {
                    correct++
                    vibrator.vibrate(VibrationEffect.createOneShot(200, 1))
                    markCorrectAnswer()
                } else if (answer != userChoice) {
                    vibrator.vibrate(VibrationEffect.createOneShot(400, 1))
                    markCorrectAnswer()
                    markWrongAnswer()
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    if (questionCount == 11) {
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Util.NAME_KEY, name)
                        intent.putExtra(Util.CORRECT_KEY, correct)
                        startActivity(intent)
                        finish()
                    } else {
                        userChoice = null
                        clearButtons()
                        hideAnswers()
                        generateQuestion()
                    }
                }, 1000)
            }
        }

        binding.firstAnswer.setOnClickListener { markSelected(binding.firstAnswer) }
        binding.secondAnswer.setOnClickListener { markSelected(binding.secondAnswer) }
        binding.thirdAnswer.setOnClickListener { markSelected(binding.thirdAnswer) }
        binding.fourthAnswer.setOnClickListener { markSelected(binding.fourthAnswer) }
    }


    private fun markWrongAnswer() {
        userChoice!!.setIconResource(R.drawable.wrong)
        userChoice!!.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.red
            )
        )
    }


    private fun markCorrectAnswer() {
        answer.setIconResource(R.drawable.correct)
        answer.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.green
            )
        )
    }


    private fun clearButtons() {
        binding.firstAnswer.text = ""
        binding.firstAnswer.icon = null

        binding.secondAnswer.text = ""
        binding.secondAnswer.icon = null

        binding.thirdAnswer.text = ""
        binding.thirdAnswer.icon = null

        binding.fourthAnswer.text = ""
        binding.fourthAnswer.icon = null
    }


    private fun generateQuestion() {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.image.startAnimation(animationFadeIn)
        binding.firstAnswer.startAnimation(animationFadeIn)
        binding.secondAnswer.startAnimation(animationFadeIn)
        binding.thirdAnswer.startAnimation(animationFadeIn)
        binding.fourthAnswer.startAnimation(animationFadeIn)

        val options = ArrayList<MaterialButton>()
        options.add(binding.firstAnswer)
        options.add(binding.secondAnswer)
        options.add(binding.thirdAnswer)
        options.add(binding.fourthAnswer)

        do {
            country = countries[(0 until countries.size).shuffled().last()]
            val temp = (0 until options.size).shuffled().last()
            if (TextUtils.isEmpty(options[temp].text)) {
                options[temp].text = country.flagName
                if (options.size == 4) {
                    binding.image.setImageResource(country.flagPicture)
                    answer = options[temp]
                    countries.remove(country)
                }
                options.remove(options[temp])
            }
        } while (options.size > 0)

        binding.questionNum.text = String.format("$questionCount/10")
        binding.progressBar.progress = questionCount

        questionCount++
    }


    private fun markSelected(view: View) {
        if (view == binding.firstAnswer) {
            binding.firstAnswer.setStrokeColorResource(R.color.pink)
            userChoice = binding.firstAnswer
            binding.firstAnswer.strokeWidth = 6
        } else {
            binding.firstAnswer.setStrokeColorResource(R.color.black)
            binding.firstAnswer.strokeWidth = 3
        }

        if (view == binding.secondAnswer) {
            binding.secondAnswer.setStrokeColorResource(R.color.pink)
            userChoice = binding.secondAnswer
            binding.secondAnswer.strokeWidth = 6
        } else {
            binding.secondAnswer.setStrokeColorResource(R.color.black)
            binding.secondAnswer.strokeWidth = 3
        }

        if (view == binding.thirdAnswer) {
            binding.thirdAnswer.setStrokeColorResource(R.color.pink)
            userChoice = binding.thirdAnswer
            binding.thirdAnswer.strokeWidth = 6
        } else {
            binding.thirdAnswer.setStrokeColorResource(R.color.black)
            binding.thirdAnswer.strokeWidth = 3
        }

        if (view == binding.fourthAnswer) {
            binding.fourthAnswer.setStrokeColorResource(R.color.pink)
            userChoice = binding.fourthAnswer
            binding.fourthAnswer.strokeWidth = 6
        } else {
            binding.fourthAnswer.setStrokeColorResource(R.color.black)
            binding.fourthAnswer.strokeWidth = 3
        }

        binding.submitButton.isEnabled = true
    }


    private fun unselect() {
        if (binding.firstAnswer.strokeWidth == 6) {
            binding.firstAnswer.setStrokeColorResource(R.color.black)
            binding.firstAnswer.strokeWidth = 3
        } else if (binding.secondAnswer.strokeWidth == 6) {
            binding.secondAnswer.setStrokeColorResource(R.color.black)
            binding.secondAnswer.strokeWidth = 3
        } else if (binding.thirdAnswer.strokeWidth == 6) {
            binding.thirdAnswer.setStrokeColorResource(R.color.black)
            binding.thirdAnswer.strokeWidth = 3
        } else {
            binding.fourthAnswer.setStrokeColorResource(R.color.black)
            binding.fourthAnswer.strokeWidth = 3
        }
    }


    private fun hideAnswers() {
        binding.firstAnswer.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.answerButtonColor
            )
        )
        binding.secondAnswer.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.answerButtonColor
            )
        )
        binding.thirdAnswer.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.answerButtonColor
            )
        )
        binding.fourthAnswer.background.setTint(
            ContextCompat.getColor(
                applicationContext,
                R.color.answerButtonColor
            )
        )
    }
}