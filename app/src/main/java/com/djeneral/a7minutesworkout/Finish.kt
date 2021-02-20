package com.djeneral.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djeneral.a7minutesworkout.databinding.FinishBinding

class Finish : AppCompatActivity() {
    private lateinit var binding: FinishBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.finishBtn.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}