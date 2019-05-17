package com.example.cadob.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import java.lang.*;


public class BMIActivity extends AppCompatActivity {
    private TextView textView; // calculated BMI value
    private EditText editText; //weight
    private EditText editText2; //height
    private Button button;
    private String BMIResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        textView = (TextView)findViewById(R.id.textView3);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        button = (Button)findViewById(R.id.button5);
    }


    public void calculateBMI(View view) {
        String sedit_text = editText.getText().toString();
        String sedit_text2 = editText2.getText().toString();
        if (sedit_text.matches("") || sedit_text2.matches(""))
            {
            textView.setText("Please enter your weight and height.");
            }
        else
            {
            Double weight = Double.parseDouble(sedit_text);
            Double height = Double.parseDouble(sedit_text2);
            Double BMI = weight / Math.pow(height, 2);

            if (BMI < 18.5)
                BMIResult = "Underweight";
            else if (BMI >= 18.5 && BMI <= 24.9)
                BMIResult = "Normal weight";
            else if (BMI > 24.9 && BMI <= 29.9)
                BMIResult = "Overweight";
            else if (BMI > 29.9)
                BMIResult = "Obesity";
            else
                BMIResult = "Error";

            textView.setText("Weight Category : " + BMIResult);
        }
    }
}
