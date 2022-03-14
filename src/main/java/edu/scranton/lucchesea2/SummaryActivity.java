package edu.scranton.lucchesea2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    TextView text;
    double startingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        text = findViewById(R.id.totalBillText);
        ImageButton roundUp = findViewById(R.id.roundUpButton);
        ImageButton roundDown = findViewById(R.id.roundDownButton);

        Intent intent = getIntent();
        double BillBeforeTip = intent.getDoubleExtra("BillBeforeTip", 0.0);
        double Tip = intent.getDoubleExtra("Tip", 0.0);
        double numOfPeople=  intent.getDoubleExtra("numOfPeople", 0.0);
        int isRounded = intent.getIntExtra("isRounded", 0);

        calculate(BillBeforeTip, Tip, numOfPeople, isRounded, intent);
        roundButtonListeners(roundUp, roundDown, intent);
    }

    public void calculate(double bill, double Tip, double people, int round, Intent intent) {
        startingValue =  (bill * Tip) / people;
        if (round == 0) {
            String perPerson = String.format("$%.2f", startingValue);
            text.setText(perPerson);
        } else if (round == 1) {
            roundUp(intent);
        } else if (round == -1){
            roundDown(intent);
        }
    }

    public void roundUp(Intent intent) {
        double val = Math.ceil(startingValue);
        text.setText(String.format("$%.2f", val));
        intent.putExtra("isRounded", 1);
    }
    public void roundDown(Intent intent) {
        double val = Math.floor(startingValue);
        text.setText(String.format("$%.2f", val));
        intent.putExtra("isRounded", -1);
    }

    public void roundButtonListeners(ImageButton up, ImageButton down, Intent intent) {
        up.setOnClickListener(view -> {
            roundUp(intent);
        });
        down.setOnClickListener(view -> {
            roundDown(intent);
        });
    }


}