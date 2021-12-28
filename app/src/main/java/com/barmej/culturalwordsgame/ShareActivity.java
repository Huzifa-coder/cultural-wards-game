package com.barmej.culturalwordsgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    public static final String KEY_OF_SHARE_IMAGE  = "Share image";
    public static final String KEY_OF_SHARE_TEXT_IMAGE  = "Share text";
    private int image;
    private ImageView imageView ;
    private EditText editText;
    private Intent shareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        imageView = findViewById(R.id.imageViewOfShare);
        editText = findViewById(R.id.editTextOfShare);
        image = (int) getIntent().getExtras().get(MainActivity.KEY_OF_SHARE);
        imageView.setImageResource(image);

    }

    public void onCilckShare(View view) {
        String s = editText.getText().toString();
        shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(MainActivity.KEY_OF_SHARE,image);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, null));
    }

}