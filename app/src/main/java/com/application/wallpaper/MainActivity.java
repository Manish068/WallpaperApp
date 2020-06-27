package com.application.wallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewClickInterface {

    ArrayList<ImageModel> imageModelArrayList;
    ImageAdapter imageAdapter;
    RecyclerView imageRecyclerview;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageModelArrayList=new ArrayList<>();
        imageRecyclerview=findViewById(R.id.pictureRecyclerView);
        imageRecyclerview.setHasFixedSize(true);

        Context context;
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this, 2);
        imageRecyclerview.setLayoutManager(gridLayoutManager);

        reference= FirebaseDatabase.getInstance().getReference().child("PictureSque");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ImageModel model=new ImageModel();
                    model.setImage_url(snapshot.getValue(ImageModel.class).getImage_url());
                    imageModelArrayList.add(model);
                }

                imageAdapter=new ImageAdapter(MainActivity.this,imageModelArrayList,MainActivity.this);
                imageRecyclerview.setAdapter(imageAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("DatabaseError", "onCancelled: "+databaseError.getMessage());

            }
        });
        

    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, EachItemDetail.class);
        intent.putExtra("selected_one", imageModelArrayList.get(position));
        startActivity(intent);
    }
}