package com.example.cadob.health_tracker;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.Toast;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphsActivity extends AppCompatActivity {
    private GraphView graph;
    private Spinner spinner;
    private Button button;
    private ArrayAdapter<String> dataAdapter;
    private String[] datas = {"BLOOD PRESSURE","BLOOD SUGAR","HEART RATE"};
    private String choose;
    double x,y,y2;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
        spinner = (Spinner)findViewById(R.id.spinner);
        button = (Button)findViewById(R.id.button10);
        graph = (GraphView) findViewById(R.id.graph);

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datas);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choose = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), choose, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void run(View view) {
        Globals g = (Globals)getApplication();
        if(choose.equals("BLOOD PRESSURE")) {
             series = new LineGraphSeries<DataPoint>();
             series.setColor(Color.RED);
             series2 = new LineGraphSeries<DataPoint>();
             series2.setColor(Color.BLUE);
             if(g.getBloodPressuresSize()<=7) {  //if <7, show first items..
                 for (int i = 0; i < 7; i++) {
                     x = i + 1;
                     if (i < g.getBloodPressuresSize()) { //if index exist
                         String str[] = g.getBloodPressure(i).split("/");
                         y = Double.parseDouble(str[0]);
                         y2 = Double.parseDouble(str[1]);
                     } else {
                         y = 0;
                         y2 = 0;
                     }
                     series.appendData(new DataPoint(x, y), true, 7);
                     series2.appendData(new DataPoint(x, y2), true, 7);

                 }
             }
             else if(g.getBloodPressuresSize()>7) {  //else, show last 7 days on graph!
                 for (int i = g.getBloodPressuresSize()-7; i < g.getBloodSugarsSize(); i++) {
                     x = i + 1;
                     if (i < g.getBloodPressuresSize()) { //if index exist
                         String str[] = g.getBloodPressure(i).split("/");
                         y = Double.parseDouble(str[0]);
                         y2 = Double.parseDouble(str[1]);
                     } else {
                         y = 0;
                         y2 = 0;
                     }
                     series.appendData(new DataPoint(x, y), true, 7);
                     series2.appendData(new DataPoint(x, y2), true, 7);

                 }
             }

             graph.addSeries(series);
             graph.addSeries(series2);

        }
        else if(choose.equals("BLOOD SUGAR")){

            series = new LineGraphSeries<DataPoint>();
            series.setColor(Color.GREEN);
            if(g.getBloodSugarsSize()<=7) {  //if <7, show first items..
                for (int i = 0; i <7; i++) {
                    x = i + 1;
                    if (i < g.getBloodSugarsSize()) {
                        y = g.getBloodSuger(i);
                    } else {
                        y = 0;
                    }
                    series.appendData(new DataPoint(x, y), true, 7);
                }
            }
            else if(g.getBloodSugarsSize()>7) {  //else, show last 7 days on graph!
                for (int i = g.getBloodSugarsSize()-7; i < g.getBloodSugarsSize(); i++) {
                    x = i + 1;
                    if (i < g.getBloodSugarsSize()) {
                        y = g.getBloodSuger(i);
                    } else {
                        y = 0;
                    }
                    series.appendData(new DataPoint(x, y), true, 7);
                }
            }
            graph.addSeries(series);

        }
        else if(choose.equals("HEART RATE")){
            series = new LineGraphSeries<DataPoint>();
            series.setColor(Color.CYAN);
            if(g.getHeartRatesSize()<=7) {  //if <7, show first items..
                for (int i = 0; i < 7; i++) {
                    x = i + 1;
                    if (i < g.getHeartRatesSize()) {
                        y = g.getHeartRate(i);
                    } else {
                        y = 0;
                    }
                    series.appendData(new DataPoint(x, y), true, 7);
                }
            }
            else if(g.getHeartRatesSize()>7) {  //else, show last 7 days on graph!
                for (int i = g.getHeartRatesSize()-7; i < g.getHeartRatesSize(); i++) {
                    x = i + 1;
                    if (i < g.getHeartRatesSize()) {
                        y = g.getHeartRate(i);
                    } else {
                        y = 0;
                    }
                    series.appendData(new DataPoint(x, y), true, 7);
                }
            }

                graph.addSeries(series);
        }
        else{

        }
    }
}
