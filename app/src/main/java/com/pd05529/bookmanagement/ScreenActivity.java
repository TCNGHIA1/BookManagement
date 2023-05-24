package com.pd05529.bookmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ScreenActivity extends AppCompatActivity {
    TextView tvHello,tvLib;
    ImageView imgSpash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_screen);
        tvHello = findViewById(R.id.tvHello);
        tvLib = findViewById(R.id.tvLib);
        imgSpash = findViewById(R.id.imgSpash);
        Glide.with(getApplicationContext()).load(R.drawable.img_spash).into(imgSpash);
        tvHello.animate().translationY(300).setDuration(2000);
        tvLib.animate().translationY(300).setDuration(2000);
        imgSpash.animate().translationY(300).setDuration(2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
        },4000);
    }
}