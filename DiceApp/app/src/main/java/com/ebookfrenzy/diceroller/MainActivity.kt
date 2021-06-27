package com.ebookfrenzy.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creates the button variable and associates it with the button view
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
    }

    //Passes the number of sides to the Dice class, gets a random number from Dice clas
    //converts the Int variable to a string and associates the random number to the view
    private fun rollDice() {
        val dice = Dice(6)
        val secondDice = Dice(6)
        val diceRoll = dice.roll()
        val secondDiceRoll = secondDice.roll()
        val resultTextView: TextView = findViewById(R.id.textView)
        val secondResultTextView: TextView = findViewById(R.id.textView2)
        resultTextView.text = diceRoll.toString()
        secondResultTextView.text = secondDiceRoll.toString()
    }
}

//Takes in the number of sides a dice has and assigns a random number to it
class Dice(private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}