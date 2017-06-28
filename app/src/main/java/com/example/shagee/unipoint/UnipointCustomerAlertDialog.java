package com.example.shagee.unipoint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class UnipointCustomerAlertDialog extends AppCompatActivity {

    private TextView firstNameTV;
    private TextView lastNameTV;
    private TextView mobileNumberTV;
    private TextView cardNumberTV;
    private TextView cardStatusTV;
    private TextView loyaltyLevelTV;
    private  TextView totalPointsTV;
    private ImageView customerIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unipoint_customer_alert_dialog);
        firstNameTV = (TextView) findViewById(R.id.firstNameTV);
        lastNameTV = (TextView) findViewById(R.id.lastNameTV);
        mobileNumberTV = (TextView) findViewById(R.id.mobileNumberTV);
        cardNumberTV = (TextView) findViewById(R.id.cardNumberTV);
        cardStatusTV = (TextView) findViewById(R.id.cardStatusTv);
        loyaltyLevelTV = (TextView) findViewById(R.id.loyaltyLevelTV);
        totalPointsTV = (TextView) findViewById(R.id.totalPointsTV);
        customerIV = (ImageView) findViewById(R.id.customerIV);

        firstNameTV.setText(getIntent().getStringExtra("firsName"));
        lastNameTV.setText(getIntent().getStringExtra("lastName"));
        mobileNumberTV.setText(getIntent().getStringExtra("mobileNumber"));
        cardNumberTV.setText(getIntent().getStringExtra("cardNumber"));
        cardStatusTV.setText(getIntent().getStringExtra("cardStatus"));
        loyaltyLevelTV.setText(getIntent().getStringExtra("loyaltyLevel"));
        totalPointsTV.setText(getIntent().getStringExtra("totalPoints"));

    }
}
