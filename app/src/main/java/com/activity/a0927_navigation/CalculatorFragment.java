package com.activity.a0927_navigation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CalculatorFragment extends Fragment {

    private TextView tvCalculation, tvResult;
    private StringBuilder currentInput = new StringBuilder();
    private double firstNumber = 0.0;
    private double secondNumber = 0.0;
    private String operator = "";
    private boolean operatorSelected = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        tvCalculation = view.findViewById(R.id.tvCalculation);
        tvResult = view.findViewById(R.id.tvResult);

        setupListeners(view);

        return view;
    }

    private void setupListeners(View view) {
        view.findViewById(R.id.btn0).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn1).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn2).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn3).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn4).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn5).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn6).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn7).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn8).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btn9).setOnClickListener(this::onNumberClicked);
        view.findViewById(R.id.btnDot).setOnClickListener(this::onNumberClicked);

        view.findViewById(R.id.btnAdd).setOnClickListener(this::onOperatorClicked);
        view.findViewById(R.id.btnSubtract).setOnClickListener(this::onOperatorClicked);
        view.findViewById(R.id.btnMultiply).setOnClickListener(this::onOperatorClicked);
        view.findViewById(R.id.btnDivide).setOnClickListener(this::onOperatorClicked);

        view.findViewById(R.id.btnClear).setOnClickListener(v -> clear());
        view.findViewById(R.id.btnBackspace).setOnClickListener(v -> backspace());
        view.findViewById(R.id.btnEqual).setOnClickListener(v -> calculateResult());
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
            try {
                firstNumber = Double.parseDouble(currentInput.toString());
            } catch (NumberFormatException e) {
                tvResult.setText("Error");
                return;
            }

            operator = ((TextView) view).getText().toString();
            currentInput.append(" ").append(operator).append(" ");
            operatorSelected = true;
            tvCalculation.setText(currentInput.toString());

        }
    }

    private void calculateResult() {
        if (operatorSelected && currentInput.length() > 0) {
            String[] tokens = currentInput.toString().split(" ");
            if (tokens.length == 3) {
                try {
                    secondNumber = Double.parseDouble(tokens[2]);

                    Log.d("CalculatorDebug", "Second number: " + secondNumber);

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
                        default:
                            Log.d("CalculatorDebug", "Unknown operator: " + operator);
                            break;
                    }

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
