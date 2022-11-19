package com.jakob.biggernumbergame

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class MainActivity : AppCompatActivity() {
    var AcScore = 0
    var AcHighscore = 0
    var language = "English"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Main
        val btnRight = findViewById<Button>(R.id.btnRight)
        val btnLeft = findViewById<Button>(R.id.btnLeft)
        val tvAcScore = findViewById<TextView>(R.id.tvAcScore)
        val tvAcHighscore = findViewById<TextView>(R.id.tvAcHighscore)
        val languageSpinner = findViewById<Spinner>(R.id.languageSpinner)

        // For Translation
        val title = findViewById<TextView>(R.id.title)
        val description = findViewById<TextView>(R.id.description)
        val tvScore = findViewById<TextView>(R.id.tvScore)
        val tvHighscore = findViewById<TextView>(R.id.tvHighscore)
        val tvlanguage = findViewById<TextView>(R.id.tvlanguage)

        val sharedPrefHigh = getSharedPreferences("highscore", Context.MODE_PRIVATE)
        AcHighscore = sharedPrefHigh.getInt("score", 0)

        tvAcScore.text = "$AcScore"
        tvAcHighscore.text = "$AcHighscore"

        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: android.view.View,
                pos: Int,
                id: Long
            ) {
                if (language == "English") {
                    title.text = getString(R.string.title)
                    description.text = getString(R.string.description)
                    tvlanguage.text = getString(R.string.language)
                } else if (language == "German") {
                    title.text = getString(R.string.title_de)
                    description.text = getString(R.string.description_de)
                    tvlanguage.text = getString(R.string.language_de)
                }
                when (languageSpinner.selectedItemPosition) {
                    0 -> language = "German"
                    1 -> language = "English"
                }
            }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
        }

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
        val tvAcScore = findViewById<TextView>(R.id.tvAcScore)
        val tvAcHighscore = findViewById<TextView>(R.id.tvAcHighscore)

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
            AcScore++
            tvAcScore.text = "$AcScore"
            if (AcScore > AcHighscore) {
                AcHighscore = AcScore
                tvAcHighscore.text = "$AcHighscore"
                val sharedPrefHigh = getSharedPreferences("highscore", Context.MODE_PRIVATE)
                val sharedPrefEdiHigh = sharedPrefHigh.edit()

                sharedPrefEdiHigh.apply {
                    putInt("score", AcHighscore)
                    apply()
                }
            }
        } else {
            // Wrong answer!!
            // Change background color
            backgroundView.setBackgroundColor(Color.RED)
            //Show a toast
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
            AcScore = 0
            tvAcScore.text = "$AcScore"
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