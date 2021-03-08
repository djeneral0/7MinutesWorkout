package com.djeneral.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.djeneral.a7minutesworkout.databinding.FinishBinding
import java.text.SimpleDateFormat
import java.util.*

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

        addDateToDB()
    }

    fun addDateToDB(){
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.e("Date: ", " $dateTime")

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        val dbHandler = SqlLiteOpenHelper(this, null)
        dbHandler.addDate(date)
        Log.i("Date Added: ", " $date")
    }
}