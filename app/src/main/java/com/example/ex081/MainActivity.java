package com.example.ex081;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent si;

    Switch sequenceType;

    EditText firstNum, sequenceD;

    String firstValue, stringD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNum = (EditText)findViewById(R.id.firstNum);
        sequenceD = (EditText)findViewById(R.id.sequenceD);

        sequenceType = (Switch)findViewById(R.id.sequenceType);

        si = new Intent(this, SolverActivity.class);
    }

    public void goSolver(View view) {
        firstValue = firstNum.getText().toString();
        stringD = sequenceD.getText().toString();

        if (!firstValue.equals("") && !stringD.equals("")
            && !firstValue.equals(".") && !stringD.equals(".")
            && !firstValue.equals("-") && !stringD.equals("-"))
        {
            // is it Arithmetic sequence (true)
            // or Geometric sequence (false)
            si.putExtra("sequenceType", sequenceType.isChecked());

            si.putExtra("firstNum", Float.parseFloat(firstValue));
            si.putExtra("sequenceD", Float.parseFloat(stringD));

            startActivity(si);
        }
        else {
            Toast.makeText(this, "Error! You have to enter values", Toast.LENGTH_SHORT).show();
        }
    }
}