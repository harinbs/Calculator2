package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private String operator;
    private double operand1 = 0;
    private double memory = 0; // For Memory Functionality
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);

        // Number button onClick
        View.OnClickListener numberListener = v -> {
            if (isNewOp) {
                result.setText("");
                isNewOp = false;
            }
            Button button = (Button) v;
            result.append(button.getText());
        };

        // Set listeners for number buttons
        setNumberListeners();

        // Set listeners for operator buttons
        setOperatorListeners();

        // Memory Functionality
        findViewById(R.id.buttonMS).setOnClickListener(v -> memory = Double.parseDouble(result.getText().toString()));
        findViewById(R.id.buttonMR).setOnClickListener(v -> result.setText(String.valueOf(memory)));
        findViewById(R.id.buttonMC).setOnClickListener(v -> memory = 0);
        findViewById(R.id.buttonMplus).setOnClickListener(v -> memory += Double.parseDouble(result.getText().toString()));

        // Equal button onClick
        findViewById(R.id.button_equal).setOnClickListener(v -> handleEqual());

        // Clear button onClick
        findViewById(R.id.button_clear).setOnClickListener(v -> clearResult());

        // Backspace button onClick
        findViewById(R.id.button_backspace).setOnClickListener(v -> {
            String currentText = result.getText().toString();
            if (!currentText.isEmpty()) {
                result.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        // Percent and Square Root operations
        findViewById(R.id.button_percent).setOnClickListener(v -> {
            double value = Double.parseDouble(result.getText().toString());
            result.setText(String.valueOf(value / 100));
        });

        findViewById(R.id.button_sqrt).setOnClickListener(v -> {
            double value = Double.parseDouble(result.getText().toString());
            result.setText(String.valueOf(Math.sqrt(value)));
        });
    }

    private void setNumberListeners() {
        findViewById(R.id.button0).setOnClickListener(v -> appendText("0"));
        findViewById(R.id.button1).setOnClickListener(v -> appendText("1"));
        findViewById(R.id.button2).setOnClickListener(v -> appendText("2"));
        findViewById(R.id.button3).setOnClickListener(v -> appendText("3"));
        findViewById(R.id.button4).setOnClickListener(v -> appendText("4"));
        findViewById(R.id.button5).setOnClickListener(v -> appendText("5"));
        findViewById(R.id.button6).setOnClickListener(v -> appendText("6"));
        findViewById(R.id.button7).setOnClickListener(v -> appendText("7"));
        findViewById(R.id.button8).setOnClickListener(v -> appendText("8"));
        findViewById(R.id.button9).setOnClickListener(v -> appendText("9"));
    }

    private void setOperatorListeners() {
        findViewById(R.id.button_plus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.button_divide).setOnClickListener(v -> setOperator("/"));
    }

    private void appendText(String text) {
        if (isNewOp) {
            result.setText("");
            isNewOp = false;
        }
        result.append(text);
    }

    private void setOperator(String op) {
        operand1 = Double.parseDouble(result.getText().toString());
        operator = op;
        isNewOp = true;
    }

    private void handleEqual() {
        double operand2 = Double.parseDouble(result.getText().toString());
        double output = 0;

        switch (operator) {
            case "+":
                output = operand1 + operand2;
                break;
            case "-":
                output = operand1 - operand2;
                break;
            case "*":
                output = operand1 * operand2;
                break;
            case "/":
                if (operand2 != 0) {
                    output = operand1 / operand2;
                } else {
                    result.setText("Error");
                    return;
                }
                break;
        }
        result.setText(String.valueOf(output));
    }

    private void clearResult() {
        result.setText("");
        operand1 = 0;
        isNewOp = true;
    }
}

