package com.barmej.culturalwordsgame;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_OF_ASEWER = "ANSWER";
    public static final String KEY_OF_SHARE  = "Share";
    public static final String KEY_OF_APP_LANG  = "app_lang";
    public static final String KEY_OF_APP_PREF  = "app_pref";

    int[] arryaImages = {R.drawable.icon_1, R.drawable.icon_2, R.drawable.icon_3, R.drawable.icon_4, R.drawable.icon_5,
            R.drawable.icon_6, R.drawable.icon_7, R.drawable.icon_8, R.drawable.icon_9, R.drawable.icon_10,
            R.drawable.icon_11, R.drawable.icon_12, R.drawable.icon_13};
    String[] arryaOfAnswers;
    String[] arryaOfDescreiption;
    Intent intent;
    private Random random;
    private ImageView imageView;
    public static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(KEY_OF_APP_PREF, MODE_PRIVATE);
        String appLang = sharedPreferences.getString(KEY_OF_APP_LANG, "");
        if(!appLang.equals(""))
            LocaleHelper.setLocale(this, appLang);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageViewOfQuation);
        arryaOfAnswers = getResources().getStringArray(R.array.answers);
        arryaOfDescreiption = getResources().getStringArray(R.array.answer_description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuChangeLang) {
            showLangugesDialoge();
            return true;
        }else
            return super.onOptionsItemSelected(item);
    }

    private void showLangugesDialoge() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.change_lang)
                .setItems(R.array.Languges, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String language = "ar";
                        switch (which) {
                            case 0:
                                language = "ar";
                                break;
                            case 1:
                                language = "en";
                                break;
                        }
                        saveLang(language);
                        LocaleHelper.setLocale(MainActivity.this, language);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                }).create();
        alertDialog.show();
    }

    private void saveLang(String lang){
        SharedPreferences sharedPreferences  = getSharedPreferences("app_pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_OF_APP_LANG, lang);
        editor.apply();
    }

    public void changeImage(View view) {
        random = new Random();
        index = random.nextInt(arryaImages.length);
        imageView.setImageDrawable(getDrawable(arryaImages[index]));
    }

    public void goToAnswerActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
        String s = arryaOfAnswers[index] + " : " + arryaOfDescreiption[index];
        intent.putExtra(KEY_OF_ASEWER, s);
        startActivity(intent);
    }

    public void shareImega(View view) {
        intent = new Intent(MainActivity.this, ShareActivity.class);
        intent.putExtra(KEY_OF_SHARE, arryaImages[index]);
        startActivity(intent);
    }

    public void onClickChangeLang(View view) {
        showLangugesDialoge();
    }
}