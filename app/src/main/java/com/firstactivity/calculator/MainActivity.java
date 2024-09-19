package com.firstactivity.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvCalculation, tvResult;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0.0;
    private double secondNumber = 0.0;
    private String operator = "";
    private boolean operatorSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCalculation = findViewById(R.id.tvCalculation);
        tvResult = findViewById(R.id.tvResult);

        setupListeners();
    }

    private void setupListeners() {
        findViewById(R.id.btn0).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn1).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn2).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn3).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn4).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn5).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn6).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn7).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn8).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btn9).setOnClickListener(this::onNumberClicked);
        findViewById(R.id.btnDot).setOnClickListener(this::onNumberClicked);

        findViewById(R.id.btnAdd).setOnClickListener(this::onOperatorClicked);
        findViewById(R.id.btnSubtract).setOnClickListener(this::onOperatorClicked);
        findViewById(R.id.btnMultiply).setOnClickListener(this::onOperatorClicked);
        findViewById(R.id.btnDivide).setOnClickListener(this::onOperatorClicked);

        findViewById(R.id.btnClear).setOnClickListener(v -> clear());
        findViewById(R.id.btnBackspace).setOnClickListener(v -> backspace());
        findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());
    }

    private void onNumberClicked(View view) {
        if (operatorSelected && currentInput.length() == 0) {
            currentInput.append(firstNumber).append(" ").append(operator).append(" ");
        }
        TextView button = (TextView) view;
        currentInput.append(button.getText().toString());
        tvCalculation.setText(currentInput.toString());
    }

    private void onOperatorClicked(View view) {
        if (currentInput.length() > 0 && !operatorSelected) {
            // Parse first number from the current input
            try {
                firstNumber = Double.parseDouble(currentInput.toString());
            } catch (NumberFormatException e) {
                tvResult.setText("Error");
                return;
            }

            // Log the first number for debugging
            Log.d("CalculatorDebug", "First number: " + firstNumber);

            // Set the operator and update the input display
            operator = ((TextView) view).getText().toString();
            currentInput.append(" ").append(operator).append(" ");
            operatorSelected = true;
            tvCalculation.setText(currentInput.toString());

            // Log the selected operator for debugging
            Log.d("CalculatorDebug", "Operator: " + operator);
        }
    }

    private void calculateResult() {
        if (operatorSelected && currentInput.length() > 0) {
            String[] tokens = currentInput.toString().split(" ");
            if (tokens.length == 3) {
                try {
                    // Parse the second number
                    secondNumber = Double.parseDouble(tokens[2]);

                    // Log the second number for debugging
                    Log.d("CalculatorDebug", "Second number: " + secondNumber);

                    double result = 0.0;

                    // Log the operator before performing the operation
                    Log.d("CalculatorDebug", "Operator in calculation: " + operator);

                    // Perform the calculation based on the operator
                    switch (operator) {
                        case "+":
                            result = firstNumber + secondNumber;
                            break;
                        case "-":
                            result = firstNumber - secondNumber;
                            break;
                        case "×":
                            result = firstNumber * secondNumber;
                            break;
                        case "÷":
                            if (secondNumber != 0) {
                                result = firstNumber / secondNumber;
                            } else {
                                tvResult.setText("Error");
                                return;
                            }
                            break;
                        default:
                            Log.d("CalculatorDebug", "Unknown operator: " + operator);
                            break;
                    }

                    // Log the result for debugging
                    Log.d("CalculatorDebug", "Result: " + result);

                    // Display the result and update the input for further calculations
                    tvResult.setText(String.format("%.2f", result));
                    currentInput.setLength(0);
                    currentInput.append(result);
                    operatorSelected = false;
                } catch (NumberFormatException e) {
                    tvResult.setText("Error");
                }
            }
        }
    }

    private void clear() {
        currentInput.setLength(0);
        tvCalculation.setText("");
        tvResult.setText("");
        operator = "";
        operatorSelected = false;
    }

    private void backspace() {
        if (currentInput.length() > 0) {
            currentInput.deleteCharAt(currentInput.length() - 1);
            tvCalculation.setText(currentInput.toString());
        }
    }
}
