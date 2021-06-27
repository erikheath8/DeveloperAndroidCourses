package com.ebookfrenzy.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creates the button variable and associates it with the button view
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }

        //Dice roll to fill the emptiness at the start of the app
        rollDice()
    }

    //Passes the number of sides to the Dice class, gets a random number from Dice class
    //converts the Int variable to a string and associates the random number to the view
    private fun rollDice() {
        //Create new Dice object with 6 sides and roll the dice
        val dice = Dice(6)
        val diceRoll = dice.roll()

        //Second dice roll
        val dice2 = Dice(6)
        val diceRoll2 = dice.roll()

        //Find the ImageView in the layout
        val diceImage: ImageView = findViewById(R.id.imageView)

        //Second dice ImageView in the layout
        val diceImage2: ImageView = findViewById(R.id.imageView2)

        //Find the ImageView in the layout
        val drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        val drawableResource2 = when (diceRoll2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        //Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        //Update the 2nd dice's ImageView with the correct drawable resource ID
        diceImage2.setImageResource(drawableResource2)

        //Update the content description
        diceImage.contentDescription = diceRoll.toString()

        //Second dice update for the content description
        diceImage2.contentDescription = diceRoll2.toString()
    }
}

//Takes in the number of sides a dice has and assigns a random number to it
class Dice (private val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}