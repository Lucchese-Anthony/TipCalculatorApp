package edu.scranton.lucchesea2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public EditText Bill;
    public EditText Tip;
    public EditText NumOfPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bill = findViewById(R.id.BillAmountDecimal);
        Tip = findViewById(R.id.TipAmountDecimal);
        NumOfPeople = findViewById(R.id.NumOfPeopleDecimal);

    }

    public void checkToStartSummaryActivity(View view) {
        String checkBill = Bill.getText().toString();
        String checkTip = Tip.getText().toString();
        String checkPeople = NumOfPeople.getText().toString();
        if(checkBill.equals("") || checkTip.equals("") || checkPeople.equals("")){
            Toast.makeText(MainActivity.this,
            "Must fill every field!", Toast.LENGTH_SHORT).show();
            return;
        }
        double bill = Double.parseDouble(checkBill);
        double tip = (Double.parseDouble(checkTip) / 100) + 1;
        double people = Double.parseDouble(checkPeople);
        if(bill == 0.0 && tip <= 1.0 && people == 0.0) {
            Toast.makeText(MainActivity.this,
                    "No field should be zero!", Toast.LENGTH_SHORT).show();
            return;
        }
        startSummaryActivity(bill, tip, people);
    }

    public void startSummaryActivity(double bill, double tip, double people) {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("BillBeforeTip", bill);
        intent.putExtra("Tip", tip);
        intent.putExtra("numOfPeople", people);
        intent.putExtra("isRounded", 0);
        startActivity(intent);
    }

    public void resetFields(View view) {
        Bill.setText("0");
        Tip.setText("0");
        NumOfPeople.setText("0");
    }
    public void setFifteenPercent(View view) {
        Tip.setText("15");
    }
    public void setEighteenPercent(View view) {
        Tip.setText("18");
    }
    public void setTwentyPercent(View view) {
        Tip.setText("20");
    }



}