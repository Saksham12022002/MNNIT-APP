package com.example.mnnitcentral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3500;
    Animation topanimation;
    ImageView mnnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topanimation= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        mnnit=findViewById(R.id.imageView);
        mnnit.setAnimation(topanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeintent = new Intent(MainActivity.this,login.class);
                startActivity(homeintent);
                finish();

            }
        },SPLASH_TIME_OUT);
    }
}
