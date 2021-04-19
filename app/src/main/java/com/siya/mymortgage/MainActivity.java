package com.siya.mymortgage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private EditText editTextPrincipal, editTextYears, editTextRate;
    private TextView textViewShowTotal, textShowMortgage;

    final byte MONTHS_IN_YEAR = 12;
    final byte PERCENT = 100;

    private DecimalFormat formatter = new DecimalFormat("#,###.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    public void buttonCalculate(View view) {
        calculate();
    }
    //make sure user enters all fields
    private boolean validateData() {
        if (editTextPrincipal.getText().toString().equals("")) {
            return false;
        }

        if (editTextYears.getText().toString().equals("")) {
            return false;
        }

        if ((editTextRate.getText().toString().equals(""))) {
            return false;
        }
        return true;
    }

    private void calculate() {

        if (validateData()) {

            double principal = Double.parseDouble(editTextPrincipal.getText().toString());

            float years = Float.parseFloat(editTextYears.getText().toString());
            int numberOfPayments = (int) (years * MONTHS_IN_YEAR);

            float annualInterest = Float.parseFloat(editTextRate.getText().toString());
            float monthlyInterest = annualInterest / PERCENT / MONTHS_IN_YEAR;

            double mortgage = (principal * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfPayments)) /
                    (Math.pow(1 + monthlyInterest, numberOfPayments) - 1));

            double showTotal = mortgage * numberOfPayments;

            textShowMortgage.setText(formatter.format(mortgage));
            textViewShowTotal.setText(formatter.format(showTotal));
            onTextShow();

        } else {
            onTextGone();
            Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
        }
    }
        //get reference to all the views
        private void initializeViews () {
            editTextPrincipal = findViewById(R.id.editTextPrincipal);
            editTextYears = findViewById(R.id.editTextYears);
            editTextRate = findViewById(R.id.editTextRate);

            Button buttonCalculate = findViewById(R.id.buttonCalculate);

            textViewShowTotal = findViewById(R.id.textViewShowTotal);
            textShowMortgage = findViewById(R.id.textShowMortgage);

        }

    //change text view visibility
    private void onTextGone (){
        textViewShowTotal.setVisibility(View.GONE);
        textShowMortgage.setVisibility(View.GONE);
    }

    private void onTextShow(){
        textViewShowTotal.setVisibility(View.VISIBLE);
        textShowMortgage.setVisibility(View.VISIBLE);
    }
}

