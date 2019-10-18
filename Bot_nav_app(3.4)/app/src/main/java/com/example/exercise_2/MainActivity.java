package com.example.exercise_2;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private LinearLayout home;
    private LinearLayout dashboard;
    private LinearLayout notifications;
    public ConstraintLayout images;
    public ImageView very_low;
    public ImageView low;
    public ImageView normal;
    public ImageView high;
    public int gender = 1;
    private double ppm = 0.0;
    public RadioGroup radioGroup;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    home.findViewById(R.id.home_layout).setVisibility(View.VISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    home.findViewById(R.id.home_layout).setVisibility(View.INVISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.VISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
                    final EditText bmi_waga = (EditText) findViewById(R.id.bmi_wagaEdit);
                    final EditText bmi_wzrost = (EditText) findViewById(R.id.bmi_wzrostEdit);
                    final TextView bmi_wynik = (TextView) findViewById(R.id.bmiWynik);
                    findViewById(R.id.bmi_oblicz).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String str1 = bmi_waga.getText().toString();
                            String str2 = bmi_wzrost.getText().toString();

                            if (TextUtils.isEmpty(str1)) {
                                bmi_waga.setError("Prosze podac wage");
                                bmi_waga.requestFocus();
                                return;
                            }

                            if (TextUtils.isEmpty(str2)) {
                                bmi_wzrost.setError("Prosze podac wzrost");
                                bmi_wzrost.requestFocus();
                                return;
                            }

                            float bmi_waga = Float.parseFloat(str1);
                            float bmi_wzrost = Float.parseFloat(str2) / 100;

                            float bmiWartosc = obliczBMI(bmi_waga, bmi_wzrost);

                            String wynikBMI = wynikBMI(bmiWartosc);

                            bmi_wynik.setText(String.valueOf(bmiWartosc + "-" + wynikBMI));
                        }
                    });
                    return true;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    home.findViewById(R.id.home_layout).setVisibility(View.INVISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.VISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.VISIBLE);

                    radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            View radioButton = radioGroup.findViewById(checkedId);
                            int index = radioGroup.indexOfChild(radioButton);
                            switch (index) {
                                case 0:
                                    gender = 0;
                                    break;
                                case 1:
                                    gender = 1;
                                    break;
                            }
                        }
                    });

                    final EditText ppm_waga = (EditText) findViewById(R.id.ppm_wagaEdit);
                    final EditText ppm_wzrost = (EditText) findViewById(R.id.ppm_wzrostEdit);
                    final EditText ppm_wiek = (EditText) findViewById(R.id.ppm_wiekEdit);
                    final TextView ppm_wynik = (TextView) findViewById(R.id.ppm_wynik);

                    findViewById(R.id.ppm_oblicz).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String str1 = ppm_waga.getText().toString();
                            String str2 = ppm_wzrost.getText().toString();
                            String str3 = ppm_wiek.getText().toString();

                            if (TextUtils.isEmpty(str1)) {
                                ppm_waga.setError("Proszę podać wagę");
                                ppm_waga.requestFocus();
                                return;
                            }

                            if (TextUtils.isEmpty(str2)) {
                                ppm_wzrost.setError("Proszę podać wzrost");
                                ppm_wzrost.requestFocus();
                                return;
                            }

                            if (TextUtils.isEmpty(str3)) {
                                ppm_wiek.setError("Proszę podać wiek");
                                ppm_wiek.requestFocus();
                                return;
                            }

                            float ppm_waga = Float.parseFloat(str1);
                            float ppm_wzrost = Float.parseFloat(str2);
                            float ppm_wiek = Float.parseFloat(str3);

                            double ppmWartosc = obliczPPM(ppm_waga, ppm_wzrost, ppm_wiek);

                            String wynikPPM = wynikPPM(ppmWartosc);

                            ppm_wynik.setText(String.valueOf(ppmWartosc + " kcal" + wynikPPM));
                        }
                    });

                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.Home);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        home = findViewById(R.id.home_layout);
        dashboard = findViewById(R.id.dashboard_layout);
        notifications = findViewById(R.id.notifications_layout);
        images = findViewById(R.id.images_layout);
        home.findViewById(R.id.home_layout).setVisibility(View.VISIBLE);
        dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
        notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
        images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
    }

    private float obliczBMI (float bmi_waga, float bmi_wzrost) {
        return (float) (bmi_waga / (bmi_wzrost * bmi_wzrost));
    }

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

    private String wynikPPM(double ppmWartosc) {

        if (ppmWartosc < 1600) {
            very_low = findViewById(R.id.imageView_veryLow);
            low = findViewById(R.id.imageView_low);
            normal = findViewById(R.id.imageView_normal);
            high = findViewById(R.id.imageView_high);

            very_low.findViewById(R.id.imageView_veryLow).setVisibility(View.VISIBLE);
            low.findViewById(R.id.imageView_low).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.imageView_normal).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.imageView_high).setVisibility(View.INVISIBLE);
            return "";
        } else if (ppmWartosc < 1800) {
            very_low = findViewById(R.id.imageView_veryLow);
            low = findViewById(R.id.imageView_low);
            normal = findViewById(R.id.imageView_normal);
            high = findViewById(R.id.imageView_high);

            very_low.findViewById(R.id.imageView_veryLow).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.imageView_low).setVisibility(View.VISIBLE);
            normal.findViewById(R.id.imageView_normal).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.imageView_high).setVisibility(View.INVISIBLE);
            return "";
        } else if (ppmWartosc < 2000) {
            very_low = findViewById(R.id.imageView_veryLow);
            low = findViewById(R.id.imageView_low);
            normal = findViewById(R.id.imageView_normal);
            high = findViewById(R.id.imageView_high);

            very_low.findViewById(R.id.imageView_veryLow).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.imageView_low).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.imageView_normal).setVisibility(View.VISIBLE);
            high.findViewById(R.id.imageView_high).setVisibility(View.INVISIBLE);
            return "";
        } else if (ppmWartosc < 2400) {
            very_low = findViewById(R.id.imageView_veryLow);
            low = findViewById(R.id.imageView_low);
            normal = findViewById(R.id.imageView_normal);
            high = findViewById(R.id.imageView_high);

            very_low.findViewById(R.id.imageView_veryLow).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.imageView_low).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.imageView_normal).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.imageView_high).setVisibility(View.VISIBLE);
            return "";
        } else {
            return "";
        }
    }



    private double obliczPPM(float ppm_waga, float ppm_wzrost, float ppm_wiek){
        if (gender == 0){
            ppm = (10*ppm_waga) + (ppm_wzrost*6.25) - (5*ppm_wiek) - 161;
        }
        else if (gender == 1){
            ppm = ((10*ppm_waga) + (ppm_wzrost*6.25) - (5*ppm_wiek) + 5);
        }
        return ppm;
    }


}
