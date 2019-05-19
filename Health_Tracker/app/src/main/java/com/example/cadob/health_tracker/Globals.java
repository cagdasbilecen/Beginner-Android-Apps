package com.example.cadob.health_tracker;

import java.util.ArrayList;

import android.app.Application;
public class Globals extends Application{
    private ArrayList<String> bloodPressures = new ArrayList<>();
    private ArrayList<Integer> bloodSugars = new ArrayList<>();
    private ArrayList<Integer> heartRates = new ArrayList<>();
    private ArrayList<Integer> weights = new ArrayList<>();
    public void addBloodPressure(String b){
        bloodPressures.add(b);
    }
    public String getBloodPressure(int b){
        return bloodPressures.get(b);
    }
    public void addBloodSugers(int b){
        bloodSugars.add(b);
    }
    public int getBloodSuger(int b){
        return bloodSugars.get(b);
    }
    public void addHeartRates(int b){
        heartRates.add(b);
    }
    public int getHeartRate(int b){
        return heartRates.get(b);
    }
    public void addWeights(int b){
        weights.add(b);
    }
    public int getWeight(int b){
        return weights.get(b);
    }
    public int getBloodPressuresSize() {
        return bloodPressures.size();
    }
    public int getBloodSugarsSize() {
        return bloodSugars.size();
    }
    public int getHeartRatesSize() {
        return heartRates.size();
    }
    public int getWeightsSize() {
        return weights.size();
    }
}