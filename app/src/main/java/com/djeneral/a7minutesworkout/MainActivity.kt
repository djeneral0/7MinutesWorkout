package com.djeneral.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djeneral.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.llStart.setOnClickListener{
//            showToat("Clicked")
            val intent = Intent(this, Exercise::class.java)
            startActivity(intent)
        }

        binding.llBMI.setOnClickListener{
            startActivity(Intent(this, BMI::class.java))
        }
    }
}