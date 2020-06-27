package com.application.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {
    public static int SPLASH_TIME_OUT = 3000;

    ImageView logo_shape;
    TextView app_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo_shape=findViewById(R.id.logo_shape);
        app_name=findViewById(R.id.app_name);


        Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce_down);
        logo_shape.setAnimation(animation);


        app_name.setAnimation(AnimationUtils.loadAnimation(this,R.anim.bounce_up));



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashintent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(splashintent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}


