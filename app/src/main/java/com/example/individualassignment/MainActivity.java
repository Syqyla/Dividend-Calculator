package com.example.individualassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtInvested, edtRate, edtMonths;
    private Button btnCalculate;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Initialize UI elements
        edtInvested = findViewById(R.id.edtInvested);
        edtRate = findViewById(R.id.edtRate);
        edtMonths = findViewById(R.id.edtMonths);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);

        // Set click listener
        btnCalculate.setOnClickListener(v -> calculateDividend());
    }

    // Dividend calculation
    private void calculateDividend() {
        String investedStr = edtInvested.getText().toString().trim();
        String rateStr = edtRate.getText().toString().trim();
        String monthsStr = edtMonths.getText().toString().trim();

        if (investedStr.isEmpty() || rateStr.isEmpty() || monthsStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double invested = Double.parseDouble(investedStr);
            double rate = Double.parseDouble(rateStr) / 100;
            int months = Integer.parseInt(monthsStr);

            if (months < 1 || months > 12) {
                Toast.makeText(this, "Months must be between 1 and 12", Toast.LENGTH_SHORT).show();
                return;
            }

            double monthlyDividend = (rate / 12) * invested;
            double totalDividend = monthlyDividend * months;

            txtResult.setText(
                    "Monthly Dividend: RM " + String.format("%.2f", monthlyDividend) +
                            "\nTotal Dividend: RM " + String.format("%.2f", totalDividend)
            );

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show();
        }
    }

    // Inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Handle menu clicks using if/else instead of switch
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        } else if (id == R.id.menu_home) {
            return true; // Already on home
        }

        return super.onOptionsItemSelected(item);
    }
}
