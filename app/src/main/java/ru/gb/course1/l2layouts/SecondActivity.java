package ru.gb.course1.l2layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String btnActTwo = "btnActTwo";
    public static String calculationResultTextView;
    // private TextView numberTextView;

    public TextView TextViewSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextViewSecondActivity = findViewById(R.id.calculation_result_text_view);
        // numberTextView = findViewById(R.id.text_view_second_activity);
        //int number = getIntent().getExtras().getInt("btnActTwo");
        //numberTextView.setText(String.valueOf(number));
    }
}