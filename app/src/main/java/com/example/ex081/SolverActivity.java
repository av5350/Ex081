package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SolverActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent gi;

    TextView firstNum, sequenceD, itemPlace, sumN;

    ListView sequenceList;

    float firstNumber, numD;

    boolean sequenceType;

    double valueInSequence, sumInSequence;

    String[] sequence = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        firstNum = (TextView)findViewById(R.id.firstNum);
        sequenceD = (TextView)findViewById(R.id.sequenceD);
        itemPlace = (TextView)findViewById(R.id.itemPlace);
        sumN = (TextView)findViewById(R.id.sumN);

        sequenceList = (ListView)findViewById(R.id.sequenceList);

        gi = getIntent();
        sequenceType = gi.getBooleanExtra("sequenceType", false);
        firstNumber = gi.getFloatExtra("firstNum", 0);
        numD = gi.getFloatExtra("sequenceD", 0);

        // fix the firstNumber value(without 0.0)
        if ((float)((int)firstNumber) == firstNumber) {
            firstNum.setText("X1 = " + (int)firstNumber);
        }
        else {
            firstNum.setText("X1 = " + firstNumber);
        }

        // fix the numD value(without 0.0)
        if ((float)((int)numD) == numD) {
            sequenceD.setText("D = " + (int)numD);
        }
        else {
            sequenceD.setText("D = " + numD);
        }

        // Arithmetic sequence (true)
        if (sequenceType){
            calculateArithmeticArray();
        }
        // Geometric sequence (false)
        else {
            calculateGeometricArray();
        }

        sequenceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sequenceList.setOnItemClickListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, sequence);
        sequenceList.setAdapter(adp);
    }

    public void returnMain(View view) {
        finish();
    }

    public void calculateGeometricArray(){
        for (int i = 0; i < 20; i++){
            // calculate the number
            valueInSequence = firstNumber + Math.pow(numD, i);
            fixArrayValue(i);
        }
    }

    public void calculateArithmeticArray(){
        for (int i = 0; i < 20; i++){
            // calculate the number
            valueInSequence = firstNumber + i * numD;
            fixArrayValue(i);
        }
    }

    public void fixArrayValue(int i){
        // fix the sequence[i] value (without 0.0)
        if ((float)((int)valueInSequence) == valueInSequence) {
            sequence[i] = String.valueOf((int)valueInSequence);
        }
        else {
            sequence[i] = String.valueOf(valueInSequence);
        }
    }

    public void fixSumValue()
    {
        // fix the sum value (without 0.0)
        if ((float)((int)sumInSequence) == sumInSequence) {
            sumN.setText("Sn = " + (int)sumInSequence);
        }
        else {
            sumN.setText("Sn = " + sumInSequence);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemPlace.setText("n = " + position);

        // Arithmetic sequence (true)
        if (sequenceType){
            sumInSequence = ((position + 1) * (2 * firstNumber + numD * (position))) / 2;
            fixSumValue();
        }
        // Geometric sequence (false)
        else {
            sumInSequence = (firstNumber * (Math.pow(numD, (position + 1)) - 1) / numD - 1);
            fixSumValue();
        }
    }
}