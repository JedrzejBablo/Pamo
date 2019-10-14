package com.example.bot_nav.ui.notifications;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.bot_nav.R;

public class NotificationsFragment extends Fragment {

    /*private NotificationsViewModel notificationsViewModel;*/

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);*/
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final EditText waga = (EditText) root.findViewById(R.id.editText3);
        final EditText wzrost = (EditText) root.findViewById(R.id.editText4);
        final EditText wiek = (EditText) root.findViewById(R.id.editText5);
        final TextView wynik = (TextView) root.findViewById(R.id.textView5);

        root.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = waga.getText().toString();
                String str2 = wzrost.getText().toString();
                String str3 = wiek.getText().toString();

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

                if (TextUtils.isEmpty(str3)) {
                    wiek.setError("Prosze podac wiek");
                    wiek.requestFocus();
                    return;
                }

                float waga = Float.parseFloat(str1);
                float wzrost = Float.parseFloat(str2);
                float wiek = Float.parseFloat(str3);

                float ppmWartosc = obliczMen(waga, wzrost, wiek);
                float ppmWartosc2 = obliczKo(waga, wzrost, wiek);

                wynik.setText(String.valueOf(ppmWartosc + " kcal"));
            }
        });

        return root;
    }

    private float obliczMen (float waga, float wzrost, float wiek) {
        return (float) ((10*waga) + (wzrost*6.25) - (5*wiek) + 5);
    }

    private float obliczKo (float waga, float wzrost, float wiek) {
        return (float) ((10*waga) + (wzrost*6.25) - (5*wiek) - 161);
    }

}