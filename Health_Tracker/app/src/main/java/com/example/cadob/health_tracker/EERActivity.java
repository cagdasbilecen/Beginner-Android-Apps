package com.example.cadob.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.Toast;


import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class EERActivity extends AppCompatActivity {
    private String[] genders = {"MAN","WOMAN"};
    private String[] daily_activites = {"SEDENTARY", "LOW ACTIVE" , "ACTIVE" , "VERY ACTIVE"};
    private Spinner spinner1; //gender
    private Spinner spinner2; //daily activities
    private TextView textView;  //result
    private ArrayAdapter<String> dataAdapterForGenders;
    private ArrayAdapter<String> dataAdapterForDailyActivities;
    private Button button; //calculate button
    private EditText editText;  //age
    private EditText editText2; // weight
    private EditText editText3; //height
    String selectedGender;
    String selectedDailyActivity;
    int selectedAge;
    Double selectedWeight;
    Double selectedHeight;
    String nothingSelected;
    Double EER = 0.0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eer);

        spinner1 = (Spinner)findViewById(R.id.spinner2);
        spinner2 = (Spinner)findViewById(R.id.spinner3);
        textView = (TextView)findViewById(R.id.textView6);
        button = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText4); // age
        editText2 = (EditText)findViewById(R.id.editText3); // weight
        editText3 = (EditText)findViewById(R.id.editText5);  //height

        //prepare adapters for spinners
        dataAdapterForGenders = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genders);
        dataAdapterForDailyActivities = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,daily_activites);
        //determine how data look
        dataAdapterForGenders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterForDailyActivities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //add prepared adapters to spinners
        spinner1.setAdapter(dataAdapterForGenders);
        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGender = spinner1.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), selectedGender, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setAdapter(dataAdapterForDailyActivities);
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDailyActivity = spinner2.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), selectedDailyActivity, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public Double calculatePA(String activity,String gender) {
        if(gender.equals("MAN")) {
            if(activity.equals("SEDENTARY")) {
                return 1.0;
            }
            else if(activity.equals("LOW ACTIVE")) {
                return 1.11;
            }
            else if(activity.equals("ACTIVE")) {
                return 1.25;
            }
            else if(activity.equals("VERY ACTIVE")) {
                return 1.48;
            }
            else {
                return -1.0;
            }
        }
        else if(gender.equals("WOMAN")) {
            if(activity.equals("SEDENTARY")) {
                return 1.0;
            }
            else if(activity.equals("LOW ACTIVE")) {
                return 1.12;
            }
            else if(activity.equals("ACTIVE")) {
                return 1.27;
            }
            else if(activity.equals("VERY ACTIVE")) {
                return 1.45;
            }
            else {
                return -1.0;
            }

        }

        else {
            return -1.0;
        }


    }


    public void run (View view){
        boolean isOk=false;
        if(!editText.getText().toString().matches("") && !editText2.getText().toString().matches("") && !editText3.getText().toString().matches("") ) {
            selectedAge = Integer.parseInt(editText.getText().toString());
            selectedWeight = Double.parseDouble(editText2.getText().toString());
            selectedHeight = Double.parseDouble(editText3.getText().toString());
            isOk=true;
        }
        else{
            textView.setText("Please fill all the blanks."); }




     if(isOk)   {
        if (selectedAge >= 19) {
            if (selectedGender.equals("MAN")) {
                EER = 662 - (9.53 * selectedAge) + calculatePA(selectedDailyActivity, selectedGender) * ((15.91 * selectedWeight) + (539.6 * selectedHeight));}
            else if (selectedGender.equals("WOMAN")) {
                EER = 354 - (6.91 * selectedAge) + calculatePA(selectedDailyActivity, selectedGender) * ((9.36 * selectedWeight) + (726 * selectedHeight)); }
            else
            { EER = -2.0;}
         }
        else {
            EER = -1.0; }

            textView.setText("EER: " + EER + "kcal/day.");
     }

        }
    }


