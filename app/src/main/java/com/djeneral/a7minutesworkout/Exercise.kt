package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import com.djeneral.a7minutesworkout.classes.Constants
import com.djeneral.a7minutesworkout.classes.ExerciseModel
import com.djeneral.a7minutesworkout.databinding.ExerciseBinding
import java.util.*
import kotlin.collections.ArrayList

class Exercise : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ExerciseBinding

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var restTimerDuration: Long = 10
    private var upcomingPosition = -1

    private var exerTimer: CountDownTimer? = null
    private  var exerProgress = 0
    private var exerTimerDuration: Long = 30

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currExercisePosition = -1

    private var tts: TextToSpeech? = null

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
        
        tts = TextToSpeech(this, this)
        exerciseList = Constants.defaultExerciseList()
        setupRestView()
    }

    private fun setRestProgressBar(){
        binding.progressBar.progress = restProgress
        upcomingPosition++
        if (upcomingPosition < exerciseList!!.size!! - 1){
            binding.tvUpcomingName.text = exerciseList!![upcomingPosition].getName()
        }else{
            binding.tvUpcomingName.visibility = View.GONE
            binding.tvUpcoming.visibility = View.GONE
        }

        restTimer = object : CountDownTimer(restTimerDuration * 1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.progressBar.progress = restTimerDuration.toInt() - restProgress
                binding.tvTimer.text = (restTimerDuration.toInt() - restProgress).toString()
            }

            override fun onFinish() {
//                showToat("Exercise will start now")
                currExercisePosition++
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
//                showToat("We will start the next rest Screen")
                if (currExercisePosition < exerciseList?.size!! - 1){
                    setupRestView()
                }else{
                    showToat("Congratulation!, you have finished the 7 minutes workout")
                }
            }
        }.start()
    }

    private fun setupRestView(){
        binding.llRestView.visibility = View.VISIBLE
        binding.llExerciseView.visibility = View.GONE

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

        binding.ivImage.setImageResource(exerciseList!![currExercisePosition].getImage())
        binding.tvExerciseName.text = exerciseList!![currExercisePosition].getName()
        speakOut(exerciseList!![currExercisePosition].getName())
    }

    override fun onDestroy() {
        if (restTimer != null){
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerTimer != null){
            exerTimer!!.cancel()
            exerProgress = 0
        }
        
        if(tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        super.onDestroy()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if (result  == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TextToSpeech", "The Language Specified is not Supported!")
            }
        }else{
            Log.e("TextToSpeech", "Initialization Failed")
        }
    }

    fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
}