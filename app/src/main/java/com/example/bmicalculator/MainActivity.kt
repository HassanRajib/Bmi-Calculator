package com.example.bmicalculator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calBtn.setOnClickListener {
            binding.calCard.setVisibility(View.VISIBLE)
            val weight = binding.weiEt.text.toString()
            val height = binding.heiEt.text.toString()

            if (validateInput(weight, height)){
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
                val bmiDigi = String.format("%.2f", bmi).toDouble()
                displayResult(bmiDigi)
            }

        }
    }

    private fun displayResult(bmiDigi: Double){
        var result = ""
        var color = 0
        var range = ""

        when{
            bmiDigi<18.50->{
                result = "Underweight"
                color = R.color.black
                range = "(Underweight range is less then 18.50)"
            }
            bmiDigi in 18.50..24.99->{
                result = "Healthy"
                color = R.color.white
                range = "(Healthy range is 18.50 - 24.99)"
            }
            bmiDigi in 25.00..29.99->{
                result = "Overweight"
                color = R.color.black
                range = "(Overweight range is 25.00 - 29.99)"
            }
            bmiDigi>29.99->{
                result = "Obese"
                color = R.color.white
                range ="(Obese range is greater then 29.99)"
            }
        }
        binding.conTxt.setTextColor(ContextCompat.getColor(this, color))
        binding.conTxt.text = bmiDigi.toString()
        binding.resTxt.setTextColor(ContextCompat.getColor(this, color))
        binding.resTxt.text = result
        binding.renTxt.setTextColor(ContextCompat.getColor(this, color))
        binding.renTxt.text = range
    }

    private fun validateInput(weight:String, height:String):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this,"Height is empty",Toast.LENGTH_SHORT).show()
                return false
            }
            else->{
                return true
            }
        }
    }
}