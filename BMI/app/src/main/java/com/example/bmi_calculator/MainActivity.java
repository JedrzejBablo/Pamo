package com.example.bmi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText waga = (EditText) findViewById(R.id.editText);
        final EditText wzrost = (EditText) findViewById(R.id.editText2);
        final TextView wynik = (TextView) findViewById(R.id.textView3);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = waga.getText().toString();
                String str2 = wzrost.getText().toString();

                if (TextUtils.isEmpty(str1)) {
                    waga.setError("Prosze podac wage");
                    waga.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(str2)) {
                    wzrost.setError("Prosze podac wzrost");
                    wzrost.requestFocus();
                    return;
                }

                float waga = Float.parseFloat(str1);
                float wzrost = Float.parseFloat(str2) / 100;

                float bmiWartosc = obliczBMI(waga, wzrost);

                String wynikBMI = wynikBMI(bmiWartosc);

                wynik.setText(String.valueOf(bmiWartosc + "-" + wynikBMI));
            }
        });

    }

    private float obliczBMI (float waga, float wzrost) {
        return (float) (waga / (wzrost * wzrost));
    }

    // Interpret what BMI means
    private String wynikBMI(float bmiWartosc) {

        if (bmiWartosc < 16) {
            return "Ciężka niedowaga";
        } else if (bmiWartosc < 18.5) {

            return "Niedowaga";
        } else if (bmiWartosc < 25) {

            return "W normie";
        } else if (bmiWartosc < 30) {

            return "Nadwaga";
        } else {
            return "Otylosc";
        }
    }

}
