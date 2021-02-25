package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.djeneral.a7minutesworkout.databinding.BmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMI : AppCompatActivity() {
    private lateinit var binding: BmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbBmi)

//        val actionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "CALCULATE BMI"

        binding.tbBmi.setNavigationOnClickListener{
            onBackPressed()
        }

        binding.btnCal.setOnClickListener{
            if (validateMetricUnits()){
                val heightValue: Float = binding.eMetricUnitheight.text.toString().toFloat() / 100
                val weightValue: Float = binding.eMetricUnitWeight.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)
                displayBMIResult(bmi)
            }else{
                showToat("Please enter valid Values.")
            }
        }
    }

    fun displayBMIResult(bmi: Float) {
        var bmiLabel: String
        var bmiDes = "Oops! You really need to take care of yourself better! Ear more!"

        when {
            bmi.compareTo(15f) <= 0 -> {
                bmiLabel = "Very Severely Underweight"
            }
            bmi.compareTo(15f) > 0 && bmi.compareTo((16f)) <= 0 -> {
                bmiLabel = "Severely Underweight"
            }
            bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0 -> {
                bmiLabel = "Underweight"
            }
            bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0 ->{
                bmiLabel = "Normal"
                bmiDes = "Congratulations! You are in good shape!"
            }
            bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <=0 ->{
                bmiLabel = "Overweight"
                bmiDes = "Oops! You really need to take care of yourself better! Workout more"
            }
            bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0 -> {
                bmiLabel = "Obese class | (Moderately obese)"
                bmiDes = "Oops! You really need to take care of yourself better! Workout more"
            }
            bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0 -> {
                bmiLabel = "Obese class || (Severely obese)"
                bmiDes = "OMG! You are in a very dangerous condition! Act now"
            }else -> {
                bmiLabel = "Obese class ||| (Very Severely obese)"
                bmiDes = "OMG! You are in a very dangerous condition! Act now"
            }
        }

        binding.tvYourBmi.visibility = View.VISIBLE
        binding.tvBmiValue.visibility = View.VISIBLE
        binding.tvBmiType.visibility = View.VISIBLE
        binding.tvBmiDesc.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding.tvBmiValue.text = bmiValue
        binding.tvBmiType.text = bmiLabel
        binding.tvBmiDesc.text = bmiDes
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        if(binding.eMetricUnitWeight.text.toString().isEmpty()){
            isValid = false
        }else if(binding.eMetricUnitheight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }

}