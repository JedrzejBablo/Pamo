package com.example.bot_nav.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bot_nav.R;

public class DashboardFragment extends Fragment {

    //private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final EditText waga = (EditText) root.findViewById(R.id.editText);
        final EditText wzrost = (EditText) root.findViewById(R.id.editText2);
        final TextView wynik = (TextView) root.findViewById(R.id.textView3);

        root.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
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

        return root;
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