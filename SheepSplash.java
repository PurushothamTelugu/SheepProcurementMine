package com.mine.procurementcopy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SheepSplash extends AppCompatActivity {

    TextView splashText;
    Animation animation;
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheep_splash);

        splashText = findViewById(R.id.text_splash);

        animation = AnimationUtils.loadAnimation(SheepSplash.this,R.anim.blink);
        splashText.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SheepSplash.this,LoginActivty.class));
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
