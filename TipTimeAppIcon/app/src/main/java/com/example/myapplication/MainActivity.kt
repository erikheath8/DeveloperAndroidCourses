package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.myapplication.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener { calculateTip() }

        //Binding instance called on the cost of service textbox calling
        //the handleKeyEvent helper method
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode,
                                    _ -> handleKeyEvent(view, keyCode) }
    }

    private fun calculateTip() {
        //Get the double input from the cost of service field
      val stringInTextField = binding.costOfServiceEditText.text.toString()
        //Converts the string attained above into a double or null
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

    //Helper function to make the keyboard as soon as the enter key is pressed
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            //Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}