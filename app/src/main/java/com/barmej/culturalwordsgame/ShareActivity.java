package com.barmej.culturalwordsgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class ShareActivity extends AppCompatActivity {
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
        Bitmap b = BitmapFactory.decodeResource(getResources(),image);
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Title", null);
        Uri imageUri =  Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);
        share.putExtra(Intent.EXTRA_TEXT, editText.getText().toString());
        startActivity(Intent.createChooser(share, "Select"));
    }

}