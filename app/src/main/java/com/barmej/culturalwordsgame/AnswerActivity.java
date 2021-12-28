package com.barmej.culturalwordsgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {
    private TextView imageView;
    private Button button;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        String answer = getIntent().getStringExtra(MainActivity.KEY_OF_ASEWER);
        imageView = findViewById(R.id.textViewOfAnswer);
        button = findViewById(R.id.button_Share);
        imageView.setText(answer);
    }


    public void backToMain(View view) {
        finish();
    }
}