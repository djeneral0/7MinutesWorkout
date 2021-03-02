package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.djeneral.a7minutesworkout.databinding.HistoryBinding

class History : AppCompatActivity() {
    private lateinit var binding: HistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbHistory)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.history).toUpperCase()

        binding.tbHistory.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}