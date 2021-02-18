package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import com.djeneral.a7minutesworkout.databinding.ExerciseBinding

class Exercise : AppCompatActivity() {
    private lateinit var binding: ExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration: Long = 10

    private var exerTimer: CountDownTimer? = null
    private  var exerProgress = 0
    private var exerTimerDuration: Long = 30

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbExercise)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbExercise.setOnClickListener {
            onBackPressed()
        }

        setupRestView()
    }

    private fun setRestProgressBar(){
        binding.progressBar.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = restTimerDuration.toInt() - restProgress
                binding.tvTimer.text = (restTimerDuration.toInt() - restProgress).toString()
            }

            override fun onFinish() {
//                showToat("Exercise will start now")
                setExerView()
            }
        }.start()
    }

    private fun setExerciseProgressBar(){
        binding.progressBarExe.progress = exerProgress
        exerTimer = object : CountDownTimer(exerTimerDuration * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                exerProgress++
                binding.progressBarExe.progress = exerTimerDuration.toInt() - exerProgress
                binding.tvExerciseTimer.text = (exerTimerDuration.toInt() - exerProgress).toString()
            }

            override fun onFinish() {
                showToat("We will start the next rest Screen")
            }
        }.start()
    }

    private fun setupRestView(){
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        setRestProgressBar()
    }

    private fun setExerView(){
        binding.llRestView.visibility = View.GONE
        binding.llExerciseView.visibility = View.VISIBLE
        if (exerTimer != null){
            exerTimer!!.cancel()
            exerProgress = 0
        }

        setExerciseProgressBar()
    }

    override fun onDestroy() {
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        super.onDestroy()
    }
}