package com.djeneral.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.djeneral.a7minutesworkout.databinding.BmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMI : AppCompatActivity() {
    private lateinit var binding: BmiBinding
    val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
    val US_UNITS_VIEW = "US_UNITS_VIEW"

    var currentVisibleView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.tbBmi)

//        val actionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.calBmi).toUpperCase()

        binding.tbBmi.setNavigationOnClickListener{
            onBackPressed()
        }

        binding.btnCal.setOnClickListener{
            when(currentVisibleView){
                METRIC_UNITS_VIEW ->{
                    when {
                        validateMetricUnits() -> {
                            val heightValue: Float = binding.eMetricUnitheight.text.toString().toFloat() / 100
                            val weightValue: Float = binding.eMetricUnitWeight.text.toString().toFloat()

                            val bmi = weightValue / (heightValue * heightValue)
                            displayBMIResult(bmi)
                        }
                        else -> { showToat("Please enter valid Values.") }
                    }
                }else -> {
                    when{
                        validateUSUnits() ->{
                            val hVFeet: String = binding.eUSUnitHeightFeet.text.toString()
                            val hVInch: String = binding.eUSUnitheightInch.text.toString()
                            val vWeight: Float = binding.eUSUnitWeight.text.toString().toFloat()

                            val heightValue = hVInch.toFloat() + hVFeet.toFloat() * 12
                            val bmi = 703 * (vWeight / (heightValue * heightValue))
                            displayBMIResult(bmi)
                        }else -> {showToat("Please enter valid Values.")}
                    }
                }
            }
        }
        visibleMetricView()
        binding.rgUnits.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbMetricUnits -> {
                    visibleMetricView()
                }
                else -> {
                    visibleUSView()
                }
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

        binding.llDisplayResult.visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
        binding.tvBmiValue.text = bmiValue
        binding.tvBmiType.text = bmiLabel
        binding.tvBmiDesc.text = bmiDes
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true

        when {
            binding.eMetricUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            binding.eMetricUnitheight.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }

    fun validateUSUnits(): Boolean {
        var isValid = true
        when {
            binding.eUSUnitWeight.text.toString().isEmpty() -> { isValid = false }
            binding.eUSUnitHeightFeet.text.toString().isEmpty() -> { isValid = false }
            binding.eUSUnitheightInch.text.toString().isEmpty() -> { isValid = false }
        }

        return isValid
    }

    private fun visibleMetricView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding.eMetricUnitWeight.text!!.clear()
        binding.eMetricUnitheight.text!!.clear()

        binding.tilMetricUnitWeight.visibility = View.VISIBLE
        binding.tilMetricUnitheight.visibility = View.VISIBLE

        binding.tilUSUnitWeight.visibility  = View.GONE
        binding.llUSUnitHeight.visibility = View.GONE
        binding.llDisplayResult.visibility = View.GONE
    }

    private fun visibleUSView(){
        currentVisibleView = US_UNITS_VIEW
        binding.tilMetricUnitWeight.visibility = View.GONE
        binding.tilMetricUnitheight.visibility = View.GONE

        binding.eUSUnitWeight.text!!.clear()
        binding.eUSUnitHeightFeet.text!!.clear()
        binding.eUSUnitheightInch.text!!.clear()

        binding.tilUSUnitWeight.visibility  = View.VISIBLE
        binding.llUSUnitHeight.visibility = View.VISIBLE
        binding.llDisplayResult.visibility = View.GONE
    }

}