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
import android.widget.Toast;

public class SolverActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    Intent gi;

    TextView firstNum, sequenceD, itemPlace, sumN;

    ListView sequenceList;

    float firstNumber, numD, sequenceSum;

    boolean sequenceType;

    String[] sequenceArr;

    float[] sumValuesArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        firstNum = (TextView)findViewById(R.id.firstNum);
        sequenceD = (TextView)findViewById(R.id.sequenceD);
        itemPlace = (TextView)findViewById(R.id.itemPlace);
        sumN = (TextView)findViewById(R.id.sumN);

        sequenceList = (ListView)findViewById(R.id.sequenceList);

        sequenceSum = 0;
        sequenceArr = new String[20];
        sumValuesArr = new float[20];

        gi = getIntent();
        sequenceType = gi.getBooleanExtra("sequenceType", false);
        firstNumber = gi.getFloatExtra("firstNum", 0);
        numD = gi.getFloatExtra("sequenceD", 0);

        // fix the firstNumber value(without 0.0)
        firstNum.setText("X1 = " + fixValue(firstNumber));

        // fix the numD value(without 0.0)
        sequenceD.setText("D = " + fixValue(numD));

        calculateArrValues();

        sequenceList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        sequenceList.setOnItemClickListener(this);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                R.layout.support_simple_spinner_dropdown_item, sequenceArr);
        sequenceList.setAdapter(adp);
    }

    public void returnMain(View view) {
        finish();
    }

    public void calculateArrValues(){
        for (int i = 0; i < 20; i++){
            // Arithmetic sequence (true)
            if (sequenceType){
                sequenceArr[i] = fixValue(firstNumber + i * numD);
            }
            // Geometric sequence (false)
            else {
                sequenceArr[i] = fixValue((float) (firstNumber * Math.pow(numD, i)));
            }
            // create the sum until now's sequence value
            sequenceSum += Float.parseFloat(sequenceArr[i]);
            sumValuesArr[i] = sequenceSum;
        }
    }

    public String fixValue(float value){
        // fix the value (without 0.0)
        if ((float)((int)value) == value) {
            return String.valueOf((int)value);
        }
        return String.valueOf(value);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemPlace.setText("n = " + (position + 1));
        sumN.setText("Sn = " + fixValue(sumValuesArr[position]));
    }
}