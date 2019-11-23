package com.example.bmi_kotlin

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var gender:Int = 0
    private var ppm:Double = 0.0

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    home_layout.visibility = View.VISIBLE
                    dashboard_layout.visibility = View.INVISIBLE
                    notifications_layout.visibility = View.INVISIBLE
                    images_layout.visibility = View.INVISIBLE
                    scrollView.visibility = View.INVISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    home_layout.visibility = View.INVISIBLE
                    dashboard_layout.visibility = View.VISIBLE
                    notifications_layout.visibility = View.INVISIBLE
                    images_layout.visibility = View.INVISIBLE
                    scrollView.visibility = View.INVISIBLE
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    home_layout.visibility = View.INVISIBLE
                    dashboard_layout.visibility = View.INVISIBLE
                    notifications_layout.visibility = View.VISIBLE
                    images_layout.visibility = View.VISIBLE
                    scrollView.visibility = View.VISIBLE

                    radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ radioGroup, i ->

                        val radioButton = radioGroup.findViewById<View>(i)
                        val index = radioGroup.indexOfChild(radioButton)
                        when (index) {
                            0 -> {
                                gender = 0
                            }
                            1 -> {
                                gender = 1
                            }
                        }
                    })

                    val ppm_waga: EditText = findViewById(R.id.ppm_wagaEdit)
                    val ppm_wzrost: EditText = findViewById(R.id.ppm_wzrostEdit)
                    val ppm_wiek: EditText = findViewById(R.id.ppm_wiekEdit)
                    val ppm_wynik: TextView = findViewById(R.id.ppm_wynik)
                    val bmi_wynik: TextView = findViewById(R.id.bmiWynik)

                    ppm_oblicz.setOnClickListener(View.OnClickListener {
                        val str1 = ppm_waga.getText().toString()
                        val str2 = ppm_wzrost.getText().toString()
                        val str3 = ppm_wiek.getText().toString()

                        if (TextUtils.isEmpty(str1)) {
                            ppm_waga.error
                            ppm_waga.requestFocus()
                            return@OnClickListener
                        }

                        if (TextUtils.isEmpty(str2)) {
                            ppm_wzrost.error
                            ppm_wzrost.requestFocus()
                            return@OnClickListener
                        }

                        if (TextUtils.isEmpty(str3)) {
                            ppm_wiek.error
                            ppm_wiek.requestFocus()
                            return@OnClickListener
                        }

                        val ppm_waga:Float = str1.toFloat()
                        val ppm_wzrost:Float = str2.toFloat()
                        val ppm_wzrost2:Float = ppm_wzrost / 100
                        val ppm_wiek:Float = str3.toFloat()

                        val ppmWartosc:Double = obliczPPM(ppm_waga, ppm_wzrost, ppm_wiek)

                        val wynikPPM:String = wynikPPM(ppmWartosc)

                        ppm_wynik.setText(ppmWartosc.toString() + " kcal" + wynikPPM.toString())

                        val bmiWartosc:Float = obliczBMI(ppm_waga, ppm_wzrost2)

                        val wynikBMI:String = wynikBMI(bmiWartosc)

                        bmi_wynik.setText(bmiWartosc.toString() + "-" + wynikBMI.toString())
                    })
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        home_layout.visibility = View.VISIBLE
        dashboard_layout.visibility = View.INVISIBLE
        notifications_layout.visibility = View.INVISIBLE
        images_layout.visibility = View.INVISIBLE
        scrollView.visibility = View.INVISIBLE

        bt_activity_changer.setOnClickListener(View.OnClickListener {
            lanuchActvity()
        })

        bt_quiz.setOnClickListener(View.OnClickListener {
            startQuiz()
        })

    }

    private fun lanuchActvity() {
        val intent = Intent(this, ChartActivity::class.java)
        intent.putExtra("q1", styczen_text_id.getText().toString())
        intent.putExtra("q2", luty_text_id.getText().toString())
        intent.putExtra("q3", marzec_text_id.getText().toString())
        intent.putExtra("q4", kwiecien_text_id.getText().toString())
        startActivity(intent)
    }

    private fun startQuiz() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)

    }

    private fun obliczBMI(ppm_waga: Float, ppm_wzrost2: Float):Float{
        return ( ppm_waga / (ppm_wzrost2 * ppm_wzrost2))
    }

    private fun obliczPPM(ppm_waga:Float, ppm_wzrost:Float, ppm_wiek:Float):Double {
     if (gender == 0) {
         ppm = ((10 * ppm_waga) + (6.25 * ppm_wzrost) - (5 * ppm_wiek) - 161)
     } else if (gender == 1) {
         ppm = ((10 * ppm_waga) + (6.25*ppm_wzrost) - (5 * ppm_wiek) + 5)
     }
     return ppm
 }

    private fun wynikBMI(bmiWartosc:Float):String{
        if (bmiWartosc < 16){
            return "Ciężka niedowaga"
        } else if (bmiWartosc < 18.5){
            return "Niedowaga"
        } else if (bmiWartosc < 25){
            return "W normie"
        } else if (bmiWartosc < 30){
            return "Nadwaga"
        } else {
            return "Otylosc"
        }
    }

    private fun wynikPPM(ppmWartosc:Double):String{
        if (ppmWartosc < 1600){
            imageView_veryLow.visibility = View.VISIBLE
            imageView_low.visibility = View.INVISIBLE
            imageView_normal.visibility = View.INVISIBLE
            imageView_high.visibility = View.INVISIBLE
            return ""
        } else if (ppmWartosc < 1800){
            imageView_veryLow.visibility = View.INVISIBLE
            imageView_low.visibility = View.VISIBLE
            imageView_normal.visibility = View.INVISIBLE
            imageView_high.visibility = View.INVISIBLE
            return ""
        }else if (ppmWartosc < 2000){
            imageView_veryLow.visibility = View.INVISIBLE
            imageView_low.visibility = View.INVISIBLE
            imageView_normal.visibility = View.VISIBLE
            imageView_high.visibility = View.INVISIBLE
            return ""
        }else if (ppmWartosc < 2400){
            imageView_veryLow.visibility = View.INVISIBLE
            imageView_low.visibility = View.INVISIBLE
            imageView_normal.visibility = View.INVISIBLE
            imageView_high.visibility = View.VISIBLE
            return ""
        } else {
            return ""
        }
    }

}
