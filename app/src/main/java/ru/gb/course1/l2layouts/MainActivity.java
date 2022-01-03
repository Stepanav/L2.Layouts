package ru.gb.course1.l2layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    TextView calculationResultTextView; // текстовое поле для вывода результата
    EditText numberField;   // поле для ввода числа
    TextView operationField;    // текстовое поле для вывода знака операции
    Double operand = null;  // операнд операции
    String lastOperation = "="; // последняя операция
    Button btnActTwo; // кнопка перехода во вторую activity
    Button dotButton; // кнопка (,)
    Button clearButton;//  кнопка (С) очистки
    Button clearLastSymbolButton;// кнопка удаления оследнего введенного символа

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initViews();
        // получаем все поля по id из activity_main.xml
        calculationResultTextView = findViewById(R.id.calculation_result_text_view);
        numberField = findViewById(R.id.number_field);
        operationField = findViewById(R.id.operation_field);
        btnActTwo = (Button) findViewById(R.id.btnActTwo);
        btnActTwo.setOnClickListener(this);
        clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(this);
        clearLastSymbolButton = (Button) findViewById(R.id.clear_last_symbol_button);
        clearLastSymbolButton.setOnClickListener(this);

        findViewById(R.id.btnActTwo).setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(SecondActivity.calculationResultTextView, operand);
            //return intent;
            startActivity(intent);

        });

    }

    public void onClick(View v) {
        if (v.getId() == R.id.clear_button) {
            numberField.getText().clear();
            calculationResultTextView.setText("");
            operationField.setText("");
        }
        if (v.getId() == R.id.clear_last_symbol_button) {
            String text = numberField.getText().toString();
            numberField.setText(text.substring(0, text.length() - 1));
        }
    }

    // сохранение состояния
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if (operand != null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    // получение ранее сохраненного состояния
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand = savedInstanceState.getDouble("OPERAND");
        calculationResultTextView.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    // обработка нажатия на числовую кнопку
    public void onNumberClick(View view) {

        Button button = (Button) view;
        numberField.append(button.getText());

        if (lastOperation.equals("=") && operand != null) {
            operand = null;
        }
    }

    // обработка нажатия на кнопку операции
    public void onOperationClick(View view) {

        Button button = (Button) view;
        String op = button.getText().toString();
        String number = numberField.getText().toString();
        // если введенно число
        if (number.length() > 0) {
            number = number.replace(',', '.');
            try {
                performOperation(Double.valueOf(number), op);
            } catch (NumberFormatException ex) {
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number;
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = number;
                    break;
                case "/":
                    if (number == 0) {
                        operand = 0.0;
                    } else {
                        operand /= number;
                    }
                    break;
                case "*":
                    operand *= number;
                    break;
                case "+":
                    operand += number;
                    break;
                case "-":
                    operand -= number;
                    break;
            }
        }
        calculationResultTextView.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}