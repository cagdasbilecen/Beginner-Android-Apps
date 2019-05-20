package com.example.cadob.health_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PHTActivity extends AppCompatActivity {

    private EditText editTextBloodPressure;
    private EditText editTextBloodSugar;
    private EditText editTextHeartRate;
    private EditText editTextWeight;
    private Button resultsButton;
    private Button seeGraphsButton;

    private TextView textViewBloodPressure;
    private TextView textViewBloodSugar;
    private TextView textViewHeartRate;
    private TextView textViewWeight;

    int g_systolic;
    int g_diastolic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pht);

        editTextBloodPressure = (EditText)findViewById(R.id.editText10);
        editTextBloodSugar = (EditText)findViewById(R.id.editText13);
        editTextHeartRate = (EditText)findViewById(R.id.editText12);
        editTextWeight = (EditText)findViewById(R.id.editText11);

        textViewBloodPressure = (TextView) findViewById(R.id.textView7);
        textViewBloodSugar = (TextView) findViewById(R.id.textView8);
        textViewHeartRate = (TextView) findViewById(R.id.textView9);
        textViewWeight = (TextView) findViewById(R.id.textView10);

        resultsButton = (Button) findViewById(R.id.button6);
        seeGraphsButton = (Button) findViewById(R.id.button7);


    }

    public String bloodPressure() {
        if(editTextBloodPressure.getText().toString().matches("[0-99999]{2}/[0-99999]{2}")) {
            String[] blood = editTextBloodPressure.getText().toString().split("/");
            int systolic = Integer.parseInt(blood[0]);
            int diastolic = Integer.parseInt(blood[1]);
            g_systolic = systolic;
            g_diastolic = diastolic;

            if (systolic < 120 && diastolic < 80)
                return "Normal.";
            else if((systolic>=120 && systolic<=129) && diastolic < 80 )
                return "Elevated.";
            else if((systolic>=130 && systolic<=139) || (diastolic >=80 && diastolic <= 89 ))
                return "High Blood Pressure.(Hypertension Stage 1)";
            else if((systolic>=140 && systolic <=180 ) ||  (diastolic >= 90 && diastolic<=120 ))
                return "High Blood Pressure.(Hypertension Stage 2)";
            else if(systolic>180 || diastolic > 180 )
                return "Hypertensive Crisis.(Seek Emergency Care)";
            else
                return "Error : Out of range.";
        }
        else {
            return "Enter your blood pressure value!";
        }
    }

    public String bloodSugar() {

        if(!editTextBloodSugar.getText().toString().matches("")) {
            int selectedSugar = Integer.parseInt(editTextBloodSugar.getText().toString());
            if(selectedSugar<70)
                return "Dangerously low, seek medical attention";
            else if(selectedSugar>=70 && selectedSugar<90)
                return "Possibly too low, get sugar if experiencing symptoms of low blood sugar or see a doctor";
            else if(selectedSugar>=90 && selectedSugar<120)
                return "Normal range";
            else if(selectedSugar>=120 && selectedSugar<160)
                return "Medium, see a doctor";
            else if(selectedSugar>=160 && selectedSugar<240)
                return "Too high, work to lower blood sugar levels";
            else if(selectedSugar>=240 && selectedSugar<300)
                return "Too high, a sign of out of control diabetes, see a doctor";
            else if(selectedSugar>=300)
                return "Very high, seek immediate medical attention";
            else {
                return "Error : Out of range!";
            }
        }
        else {
            return "Enter your blood sugar value!";
        }

    }

    public String heartRate() {
        if(!editTextHeartRate.getText().toString().matches("")) {
            int selectedHeartRate = Integer.parseInt(editTextHeartRate.getText().toString());
            if(selectedHeartRate >=60 && selectedHeartRate <=100)
                return "Normal heart rate.";
            else if(selectedHeartRate<60)
                return "Low heart rate. See doctor!";
            else if(selectedHeartRate>100)
                return "High heart rate.See doctor!";
            else
                return "Error : Out of range!";
        }
        else {
            return "Enter your heart rate value!";
        }
    }

    public String weight() {
        if(!editTextWeight.getText().toString().matches("")) {
            return editTextWeight.getText().toString();
        }
        else {
            return "You did not enter weight value";
        }
    }

    public void run(View view) {
        Globals g = (Globals)getApplication();
        boolean isSaved=false;

        textViewBloodPressure.setText("Blood Pressure Result: " + bloodPressure());
        if(!editTextBloodPressure.getText().toString().matches("") && editTextBloodPressure.getText().toString().matches("[0-99999]{2}/[0-99999]{2}") ) {
            g.addBloodPressure(editTextBloodPressure.getText().toString());
            isSaved=true;
        }
        else if(editTextBloodPressure.getText().toString().matches("") || !editTextBloodPressure.getText().toString().matches("[0-99999]{2}/[0-99999]{2}") ) {
            if(!editTextBloodPressure.getText().toString().matches("[0-99999]{2}/[0-99999]{2}") && !editTextBloodPressure.getText().toString().matches("") )
                Toast.makeText(getApplicationContext(), "Wrong blood pressure format!", Toast.LENGTH_LONG).show();

        }

        textViewBloodSugar.setText("Blood Sugar Result: " + bloodSugar());
        if(!editTextBloodSugar.getText().toString().matches("")) {
            g.addBloodSugers(Integer.parseInt(editTextBloodSugar.getText().toString()));
            isSaved=true;
        }
        textViewHeartRate.setText("Heart Rate Result: " + heartRate());
        if(!editTextHeartRate.getText().toString().matches("")) {
            g.addHeartRates(Integer.parseInt(editTextHeartRate.getText().toString()));
            isSaved=true;
        }
        textViewWeight.setText("Your Weight: " + weight());
        if(!editTextWeight.getText().toString().matches("")) {
            g.addWeights(Integer.parseInt(editTextWeight.getText().toString()));
            isSaved = true;
        }
        if(isSaved)
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        else if(!isSaved)
            Toast.makeText(getApplicationContext(), "Not Saved!", Toast.LENGTH_LONG).show();
        }



    public void graphs(View view) {
        Intent intent = new Intent(this, GraphsActivity.class);
        startActivity(intent);
    }














}
