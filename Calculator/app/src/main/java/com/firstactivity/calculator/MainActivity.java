package com.firstactivity.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvCalculation, tvResult;
    private StringBuilder currentInput = new StringBuilder();
    private String operator = "";
    private double firstNumber = 0.0;
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
        TextView button = (TextView) view;
        currentInput.append(button.getText().toString());
        tvCalculation.setText(currentInput.toString());
    }

    private void onOperatorClicked(View view) {
        if (currentInput.length() > 0) {
            if (!operatorSelected) {
                firstNumber = Double.parseDouble(currentInput.toString());
                operator = ((TextView) view).getText().toString();
                currentInput.append(" " + operator + " ");
                operatorSelected = true;
            } else {
                operator = ((TextView) view).getText().toString();
                currentInput.setCharAt(currentInput.length() - 2, operator.charAt(0));
            }
            tvCalculation.setText(currentInput.toString());
        }
    }

    private void calculateResult() {
        if (operatorSelected && currentInput.length() > 0) {
            String[] tokens = currentInput.toString().split(" ");
            if (tokens.length == 3) {
                double secondNumber = Double.parseDouble(tokens[2]);
                double result = 0.0;
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
                }
                tvResult.setText(String.format("%.2f", result));
                currentInput.setLength(0);
                currentInput.append(result);
                operatorSelected = false;
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
