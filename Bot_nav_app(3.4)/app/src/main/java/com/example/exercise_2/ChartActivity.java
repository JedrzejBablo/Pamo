package com.example.exercise_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class ChartActivity extends AppCompatActivity {
    private Button mBtGoBack;
    private TextView mTextMessage;
    private int q1, q2, q3, q4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        q1 = Integer.valueOf(intent.getStringExtra("q1"));
        q2 = Integer.valueOf(intent.getStringExtra("q2"));
        q3 = Integer.valueOf(intent.getStringExtra("q3"));
        q4 = Integer.valueOf(intent.getStringExtra("q4"));
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String htmlData = "<html>"
                + "  <head>"
                + "    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "    <script type=\"text/javascript\">"
                + "      google.charts.load('current', {'packages':['corechart']});"
                + "      google.charts.setOnLoadCallback(drawChart);"

                + "      function drawChart() {"

                + "        var data = google.visualization.arrayToDataTable(["
                + "          ['Kg', 'Waga'],"
                + "          ['Kwartał 1',     " + q1 + "],"
                + "          ['Kwartał 2',      + " + q2 + "],"
                + "          ['Kwartał 3',  " + q3 + "],"
                + "          ['Kwartał 4', " + q4 + "],"
                + "        ]);"

                + " var options = {\n" +
                "          title: 'Zmiana w czasie',\n" +
                "          curveType: 'function',\n" +
                "          legend: { position: 'bottom' }\n" +
                "        };"

                + "        \n" +
                "        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));"

                + "        chart.draw(data, options);"
                + "      }"
                + "    </script>"
                + "  </head>"
                + "  <body>"
                + "    <div id=\"curve_chart\" style=\"width: 900px; height: 500px;\"></div>"
                + "  </body>"
                + "</html>";
        myWebView.loadData(htmlData, "text/html", "UTF-8");

        mBtGoBack = (Button) findViewById(R.id.bt_go_back);

        mBtGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
