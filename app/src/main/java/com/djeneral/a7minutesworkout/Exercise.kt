package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djeneral.a7minutesworkout.databinding.ExerciseBinding

class Exercise : AppCompatActivity() {
    private lateinit var binding: ExerciseBinding

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
    }
}