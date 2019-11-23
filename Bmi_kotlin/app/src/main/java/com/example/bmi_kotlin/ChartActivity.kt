package com.example.bmi_kotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_chart.*

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        var q1 = Integer.valueOf(intent.getStringExtra("q1"))
        var q2 = Integer.valueOf(intent.getStringExtra("q2"))
        var q3 = Integer.valueOf(intent.getStringExtra("q3"))
        var q4 = Integer.valueOf(intent.getStringExtra("q4"))

        webview.settings.javaScriptEnabled = true
        val htmlData = ("<html>"
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
                "          title: 'Zmiana wagi w czasie',\n" +
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
                + "</html>")
        webview.loadData(htmlData, "text/html", "UTF-8")
        bt_go_back.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

}