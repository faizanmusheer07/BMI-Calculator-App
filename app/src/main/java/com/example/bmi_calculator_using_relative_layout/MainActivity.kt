package com.example.bmi_calculator_using_relative_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bmi_calculator_using_relative_layout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.heightSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                binding.tvHeightValue.text = p1.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })

        binding.btnCalculateBmi.setOnClickListener {
            if (binding.tvHeightValue.text.toString().isNotEmpty() &&
                binding.edWeight.text.toString().isNotEmpty()
            ) {
//                val age = Integer.parseInt(binding.edAge.text.toString())
                val weight = Integer.parseInt(binding.edWeight.text.toString())
                val height = Integer.parseInt(binding.tvHeightValue.text.toString())
                calculateBmi( weight, height)
            }
        }
    }

    private fun calculateBmi( weight: Int, height: Int) {
        val heightInMeter = height / 100.0
        val bmi = weight.toDouble() / (heightInMeter * heightInMeter)

        Toast.makeText(this, "Result is $bmi", Toast.LENGTH_SHORT).show()

        val bmiCategory = when {
            bmi < 16 -> "Severely Underweight"
            bmi < 18.5 -> "Underweight"
            bmi < 25 -> "Normal"
            bmi < 30 -> "Overweight"
            else -> "Obese"
        }
         val bmiResult = "Your BMI is: %.2f  \nCategory: $bmiCategory".format(bmi)
        binding.tvResultView.visibility = View.VISIBLE
        binding.tvResultView.text = bmiResult


        val alterDialog = AlertDialog.Builder(this)
            .setTitle("BMI RESULT")
            .setMessage(bmiResult)
            .setPositiveButton("OK", null)
            .create()
        alterDialog.show()
    }
}