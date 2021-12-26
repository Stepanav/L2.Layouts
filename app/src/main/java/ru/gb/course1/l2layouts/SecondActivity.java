package ru.gb.course1.l2layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    public static final String btnActTwo = "btnActTwo";
    private TextView numberTextView;

    public static Intent getIntentForLaunch(Context context, Double number) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(SecondActivity.btnActTwo, number);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        numberTextView = findViewById(R.id.number_text_view);
        int number = getIntent().getExtras().getInt("btnActTwo");
        numberTextView.setText(String.valueOf(number));
    }
}