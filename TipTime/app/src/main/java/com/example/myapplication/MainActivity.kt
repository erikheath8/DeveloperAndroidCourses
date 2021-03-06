package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        //Converts the Editable to a string
      val stringInTextField = binding.costOfService.text.toString()
        //Converts the string attained aboved into a double or null
      val cost = stringInTextField.toDoubleOrNull()
        //Checks to see if the entered tip amount was null or $0.0 and returns 0.0 to the
        //displayTip function
        if (cost == null || cost == 0.0) {
            displayTip(0.0)
            return
        }
        //Variable assigned the percentage for the corresponding selected radio button
      val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
          R.id.option_twenty_percent -> 0.2
          R.id.option_eighteen_percent -> 0.18
          else -> 0.15
      }
        //Calculating the tip amount using var bc the amount maybe rounded up
      var tip = tipPercentage * cost
        //Checks to see if the switch is selected to round up with the ceil method
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        displayTip(tip)
    }

    private fun displayTip(tip: Double){
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}