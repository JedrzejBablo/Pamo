package com.example.bmi_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    private val mQuestions = Questions()
    private var mAnswer: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        var r: Int = 0
        var mScore: Int = 0
        score.setText("Wynik: $mScore")
        updateQuestion(r)

        answer1.setOnClickListener {
            if (answer1.text === mAnswer) {
                mScore++
                score.text = "Wynik : $mScore"
                r++
                updateQuestion(r)
            } else {
                gameOver(mScore)
            }
        }

        answer2.setOnClickListener {
            if (answer2.text === mAnswer) {
                mScore++
                score.text = "Wynik : $mScore"
                r++
                updateQuestion(r)
            } else {
                gameOver(mScore)
            }
        }

        answer3.setOnClickListener {
            if (answer3.text === mAnswer) {
                mScore++
                score.text = "Wynik : $mScore"
                r++
                updateQuestion(r)
            } else {
                gameOver(mScore)
            }
        }

        answer4.setOnClickListener {
            if (answer4.text === mAnswer) {
                mScore++
                score.text = "Wynik : $mScore"
                r++
                updateQuestion(r)
            } else {
                gameOver(mScore)
            }
        }
    }

    private fun updateQuestion(num: Int) {
        if (num > 5) {
            endTheGame(num)
        } else {
            question.setText(mQuestions.getQuestion(num))
            answer1.setText(mQuestions.getChoice1(num))
            answer2.setText(mQuestions.getChoice2(num))
            answer3.setText(mQuestions.getChoice3(num))
            answer4.setText(mQuestions.getChoice4(num))
            mAnswer = mQuestions.getCorrectAnwer(num)
        }
    }

    private fun gameOver(mScore:Int) {
        val alertDialogBuilder =
            AlertDialog.Builder(this@QuizActivity)
        alertDialogBuilder
            .setMessage("Zła odpowiedz udało Ci się uzyskać  $mScore punktów.")
            .setCancelable(false)
            .setPositiveButton(
                "Zacznij od nowa"
            ) { dialogInterface, i ->
                startActivity(Intent(applicationContext, QuizActivity::class.java))
                finish()
            }
            .setNegativeButton(
                "Wyjscie"
            ) { dialogInterface, i ->
                Toast.makeText(this@QuizActivity, "", Toast.LENGTH_LONG).show()
                finish()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun endTheGame(mScore:Int) {
        val alertDialogBuilder =
            AlertDialog.Builder(this@QuizActivity)
        alertDialogBuilder
            .setMessage("Gratulacje uzyskałeś  $mScore punktów.")
            .setCancelable(false)
            .setPositiveButton(
                "Zacznij od nowa"
            ) { dialogInterface, i ->
                startActivity(Intent(applicationContext, QuizActivity::class.java))
                finish()
            }
            .setNegativeButton(
                "Wyjscie"
            ) { dialogInterface, i ->
                Toast.makeText(this@QuizActivity, "", Toast.LENGTH_LONG).show()
                finish()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}