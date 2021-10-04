package com.example.numbersgameapp

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var myCL: ConstraintLayout
    private lateinit var input : EditText
    private lateinit var guessBtn : Button
    private lateinit var guessArray: ArrayList<String>
    private lateinit var textView : TextView
    private lateinit var rv : RecyclerView
    private var guesses = 3
    var someNumber: Int = 0
    var myMessage: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCL = findViewById(R.id.myCL)
        guessArray = ArrayList()

        rv = findViewById<RecyclerView>(R.id.rvMain)
        rv.adapter = GuessRV(this , guessArray)
        rv.layoutManager= LinearLayoutManager(this)

        textView = findViewById(R.id.textView)
        input = findViewById(R.id.input)
        guessBtn = findViewById(R.id.btn)
        guessBtn.setOnClickListener {
            if(isNumber(input.text.toString())){
                checkGuess(input.text.toString())
            }else{
                Snackbar.make(myCL, "Please enter numbers only!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("myNumber", someNumber)
        outState.putString("myMessage", myMessage)
    }
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        someNumber= savedInstanceState.getInt("myNumber", 0)
        myMessage = savedInstanceState.getString("myMessage", "No Message")
    }
    private fun checkGuess(inpu : String){
      val random = Random.nextInt(10)

        if(inpu.contentEquals(random.toString()) ){
            textView.setTextColor(Color.GREEN)
            textView.text = "You Got it!!"

        }else{
            textView.setTextColor(Color.RED)
            textView.text = "Wrong try again! "
            guesses --
            guessArray.add("You guessed $inpu")
            guessArray.add("You have $guesses guesses left")
            if(guesses == 0){
                guessArray.add("You lose - The correct answer was $random")
                guessArray.add("Game Over")
                showAlertDialog("You lose...\nThe correct answer was $random.\n\nPlay again?")
            }
        }
        input.text.clear()
        input.clearFocus()
        rv.adapter?.notifyDataSetChanged()
    }

    private fun isNumber(s: String?): Boolean {
        return if (s.isNullOrEmpty()) false else s.all { Character.isDigit(it) }
    }

    private fun showAlertDialog(title: String) {
        // build alert dialog
        val dialogBuilder = AlertDialog.Builder(this)
        // set message of alert dialog
        dialogBuilder.setMessage(title)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton("Yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })
            // negative button text and action
            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Game Over")
        // show alert dialog
        alert.show()
    }

}

