package com.example.exercise_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private LinearLayout home;
    private LinearLayout dashboard;
    private LinearLayout notifications;
    private ScrollView scrollView;
    public ConstraintLayout images;
    public ImageView very_low;
    public ImageView low;
    public ImageView normal;
    public ImageView high;
    public int gender = 1;
    private double ppm = 0.0;
    public RadioGroup radioGroup;

    private Button  activityChanger, quizButton;
    private EditText q1, q2, q3, q4;
    private TextView welcomeMessage;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    home.findViewById(R.id.home_layout).setVisibility(View.VISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
                    scrollView.findViewById(R.id.scrollView).setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    home.findViewById(R.id.home_layout).setVisibility(View.INVISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.VISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
                    scrollView.findViewById(R.id.scrollView).setVisibility(View.INVISIBLE);

                    findViewById(R.id.bt_activity_changer);
                    return true;

                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    home.findViewById(R.id.home_layout).setVisibility(View.INVISIBLE);
                    dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
                    notifications.findViewById(R.id.notifications_layout).setVisibility(View.VISIBLE);
                    images.findViewById(R.id.images_layout).setVisibility(View.VISIBLE);
                    scrollView.findViewById(R.id.scrollView).setVisibility(View.VISIBLE);

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
                    final TextView bmi_wynik = (TextView) findViewById(R.id.bmiWynik);

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
                            float ppm_wzrost2 = ppm_wzrost / 100;
                            float ppm_wiek = Float.parseFloat(str3);

                            double ppmWartosc = obliczPPM(ppm_waga, ppm_wzrost, ppm_wiek);

                            String wynikPPM = wynikPPM(ppmWartosc);

                            ppm_wynik.setText(String.valueOf(ppmWartosc + " kcal" + wynikPPM));

                            float bmiWartosc = obliczBMI(ppm_waga, ppm_wzrost2);

                            String wynikBMI = wynikBMI(bmiWartosc);

                            bmi_wynik.setText(String.valueOf(bmiWartosc + "-" + wynikBMI));
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
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        home = findViewById(R.id.home_layout);
        dashboard = findViewById(R.id.dashboard_layout);
        notifications = findViewById(R.id.notifications_layout);
        images = findViewById(R.id.images_layout);
        scrollView = findViewById(R.id.scrollView);
        home.findViewById(R.id.home_layout).setVisibility(View.VISIBLE);
        dashboard.findViewById(R.id.dashboard_layout).setVisibility(View.INVISIBLE);
        notifications.findViewById(R.id.notifications_layout).setVisibility(View.INVISIBLE);
        images.findViewById(R.id.images_layout).setVisibility(View.INVISIBLE);
        scrollView.findViewById(R.id.scrollView).setVisibility(View.INVISIBLE);
        welcomeMessage = findViewById(R.id.welcome_message_textView);
        activityChanger = findViewById(R.id.bt_activity_changer);
        quizButton = findViewById(R.id.bt_quiz);
        q1 = findViewById(R.id.styczen_text_id);
        q2 = findViewById(R.id.luty_text_id);
        q3 = findViewById(R.id.marzec_text_id);
        q4 = findViewById(R.id.kwiecien_text_id);



        activityChanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launchActivity();
            }
        });

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startQuiz();
            }
        });

    }

    private void launchActivity() {

        Intent intent = new Intent(this, ChartActivity.class);
        intent.putExtra("q1", q1.getText().toString());
        intent.putExtra("q2", q2.getText().toString());
        intent.putExtra("q3", q3.getText().toString());
        intent.putExtra("q4", q4.getText().toString());
        startActivity(intent);
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private float obliczBMI (float ppm_waga, float ppm_wzrost2) {
        return (float) (ppm_waga / (ppm_wzrost2 * ppm_wzrost2));
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
