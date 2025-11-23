package com.example.individualassigment_afham;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText eVehiclePrice, eLoanPeriod, eDownPayment, eInterestRate;
    TextView txtLoanAmount, txtTotalInterest, txtTotalPayment, txtMonthlyPayment;
    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        eVehiclePrice = findViewById(R.id.eVehiclePrice);
        eLoanPeriod = findViewById(R.id.eLoanPeriod);
        eDownPayment = findViewById(R.id.eDownPayment);
        eInterestRate = findViewById(R.id.eInterestRate);

        txtLoanAmount = findViewById(R.id.txtLoanAmount);
        txtTotalInterest = findViewById(R.id.txtTotalInterest);
        txtTotalPayment = findViewById(R.id.txtTotalPayment);
        txtMonthlyPayment = findViewById(R.id.txtMonthlyPayment);

        btnCalculate = findViewById(R.id.btnCalculate);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateLoan();
            }


        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    // 1. This method puts the menu on the screen
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // 2. This method handles what happens when you click a button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // We are already on Home, so we do nothing or just show a message
            return true;
        }
        else if (id == R.id.nav_about) {
            // Navigate to AboutActivity
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void calculateLoan() {
        // 1. **Input Validation:** Check if any field is empty.
        if (eVehiclePrice.getText().toString().isEmpty() || eDownPayment.getText().toString().isEmpty() || eLoanPeriod.getText().toString().isEmpty() || eInterestRate.getText().toString().isEmpty()) {
            // Show a Toast message to the user: "Please fill in all fields."
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return; // Stop the calculation
        }

        // 2. **Get and Convert Inputs:** Convert the text input to double-precision numbers.
        double vehiclePrice = Double.parseDouble(eVehiclePrice.getText().toString());
        double downPayment = Double.parseDouble(eDownPayment.getText().toString());
        double loanPeriodYears = Double.parseDouble(eLoanPeriod.getText().toString());
        double annualInterestRate = Double.parseDouble(eInterestRate.getText().toString()); // e.g., 3.5

        // 3. **Implement the Formulas:**

        // i. Loan Amount = Vehicle Price - Down Payment
        double loanAmount = vehiclePrice - downPayment;

        // ii. Total Interest = Loan Amount * (Interest Rate / 100) * Loan Period
        double totalInterest = loanAmount * (annualInterestRate / 100.0) * loanPeriodYears;

        // iii. Total Payment = Loan Amount + Total Interest
        double totalPayment = loanAmount + totalInterest;

        // iv. Monthly Payment = Total Payment / (Loan Period * 12)
        double monthlyPayment = totalPayment / (loanPeriodYears * 12);

        // 4. **Display the Results:** Format the doubles to two decimal places (currency format) and display them in the TextViews.
        DecimalFormat df = new DecimalFormat("#,##0.00");

        txtLoanAmount.setText("Loan Amount: RM " + df.format(loanAmount));
        txtTotalInterest.setText("Total Interest: RM " + df.format(totalInterest));
        txtTotalPayment.setText("Total Payment: RM " + df.format(totalPayment));
        txtMonthlyPayment.setText("Monthly Payment: RM" + df.format(monthlyPayment));
    }
}
