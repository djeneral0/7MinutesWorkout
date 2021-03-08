package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.djeneral.a7minutesworkout.adapter.HistoryAdapter
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

        getAllCompletedDate()
    }

    fun getAllCompletedDate(){
        val dbHandle = SqlLiteOpenHelper(this, null)
        val completedList = dbHandle.getList()

        if(completedList.size > 0){
            binding.tvHistory.visibility = View.VISIBLE
            binding.rvHistory.visibility = View.VISIBLE
            binding.tvNoDate.visibility = View.GONE

            binding.rvHistory.layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, completedList)
            binding.rvHistory.adapter = historyAdapter
        }else{
            binding.tvHistory.visibility = View.GONE
            binding.rvHistory.visibility = View.GONE
            binding.tvNoDate.visibility = View.VISIBLE
        }
    }
}