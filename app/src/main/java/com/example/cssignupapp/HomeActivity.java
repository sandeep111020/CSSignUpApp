package com.example.cssignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {
    String Name, img;
    CircleImageView imgvi;
    TextView tv;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgvi=findViewById(R.id.imgvi);
        logout=findViewById(R.id.logout);
        tv=findViewById(R.id.text);
        Name=getIntent().getStringExtra("name");
        img=getIntent().getStringExtra("img");
        tv.setText("Hi!! "+Name+"\n Welocome to Celebrity Schools");
        Picasso.get().load(img).placeholder(R.drawable.dasprofile).into(imgvi);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences =getSharedPreferences("ckeckbox", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("remember","false");
                editor.apply();

                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}