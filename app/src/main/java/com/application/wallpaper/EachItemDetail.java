package com.application.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class EachItemDetail extends AppCompatActivity {

    ImageView image;
    Intent intent;
    MaterialButton setWallpaperButton;

    WallpaperManager wallpaperManager;
    Bitmap bitmap1,bitmap2;
    DisplayMetrics displayMetrics;
    int Width,Height;
    BitmapDrawable bitmapDrawable;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eachitem_detail);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




        image=findViewById(R.id.imageView);
        intent=getIntent();
        setWallpaperButton=findViewById(R.id.setwallpaper_button);

        final ImageModel imageModel=intent.getParcelableExtra("selected_one");
        assert imageModel != null;
        Picasso.get().load(imageModel.getImage_url()).into(image);


        wallpaperManager=WallpaperManager.getInstance(getApplicationContext());



        setWallpaperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetScreenWidthHeight();
                try {

                    URL url = new URL(imageModel.getImage_url());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    bitmap1 = BitmapFactory.decodeStream(input);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                bitmap2=Bitmap.createScaledBitmap(bitmap1,Width,Height,true);

                wallpaperManager=WallpaperManager.getInstance(EachItemDetail.this);

                try {
                    wallpaperManager.setBitmap(bitmap2);
                    wallpaperManager.suggestDesiredDimensions(Width,Height);
                    Toast.makeText(EachItemDetail.this,"Wallpaper set",Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    e.printStackTrace();

                }
            }
        });


    }



    private void GetScreenWidthHeight() {
        displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        Width=displayMetrics.widthPixels;
        Height=displayMetrics.heightPixels;


    }
}