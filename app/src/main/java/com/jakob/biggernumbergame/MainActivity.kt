package com.jakob.biggernumbergame

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnLeft = findViewById<Button>(R.id.btnLeft)

        assignNumbersToButtons()

        btnLeft.setOnClickListener {
            checkAnwser(true)

            assignNumbersToButtons()
        }

        btnRight.setOnClickListener {
            checkAnwser(false)

            assignNumbersToButtons()
        }
    }

    private fun checkAnwser(isLeftButtonSelected: Boolean) {
        val backgroundView = findViewById<ConstraintLayout>(R.id.backgroundView)
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnLeft = findViewById<Button>(R.id.btnLeft)

        val leftNum: Int = btnLeft.text.toString().toInt()
        val rightNum: Int = btnRight.text.toString().toInt()

        val isAnwserCorrect = if (isLeftButtonSelected) {
            leftNum > rightNum
        } else {
            rightNum > leftNum
        }

        if (isAnwserCorrect) {
            // Correct answer!!
            // Change background color
            backgroundView.setBackgroundColor(Color.GREEN)
            //Show a toast
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            // Wrong answer!!
            // Change background color
            backgroundView.setBackgroundColor(Color.RED)
            //Show a toast
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun assignNumbersToButtons() {
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnLeft = findViewById<Button>(R.id.btnLeft)

        val r = Random()
        val leftNum: Int = r.nextInt(10)

        var rightNum: Int = leftNum
        while (rightNum == leftNum) {
            rightNum = r.nextInt(10)
        }

        btnLeft.text = leftNum.toString()
        btnRight.text = rightNum.toString()
    }
}