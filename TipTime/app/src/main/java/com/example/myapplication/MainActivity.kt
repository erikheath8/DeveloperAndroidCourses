package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    fun calculateTip() {
        //Converts the Editable to a string
      val stringInTextField = binding.costOfService.text.toString()
        //Converts the string attained aboved into  a double
      val cost = stringInTextField.toDouble()
        //Sets the var for the selected radio button for the tip options radio buttons
      val selectedId = binding.tipOptions.checkedRadioButtonId
        //Variable assigned the percentage for the corresponding selected radio button
      val tipPercentage = when (selectedId) {
          R.id.option_twenty_percent -> 0.2
          R.id.option_eighteen_percent -> 0.18
          else -> 0.15
      }
        //Calculating the tip amount using var bc the amount maybe rounded up
      var tip = tipPercentage * cost
        //Checking to see if the binding radio button is selected
        val roundUp = binding.roundUpSwitch.isChecked
        //Checks to see if the switch is selected to round up with the ceil method
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        NumberFormat.getCurrencyInstance()

    }

}