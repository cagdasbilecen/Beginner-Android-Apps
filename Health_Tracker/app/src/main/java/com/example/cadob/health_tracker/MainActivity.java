package com.example.cadob.health_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void BMI(View view) {
        Intent intent = new Intent(this, BMIActivity.class);
        startActivity(intent);
    }

    public void EER(View view) {
        Intent intent = new Intent(this, EERActivity.class);
        startActivity(intent);
    }
    public void PHT(View view) {
        Intent intent = new Intent ( this, PHTActivity.class);
        startActivity(intent);
    }


}
