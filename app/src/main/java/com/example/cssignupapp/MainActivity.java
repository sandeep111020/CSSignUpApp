package com.example.cssignupapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements  BottomsheetdialogFragment.ItemClickListener{
    private static final String TAG  = "hi" ;

    EditText email,password;
    LinearLayout adminlayout;
    Button login,admin,register;
    TextView textView;
    String Semail,Spassword,clientid;
    private  static int RC_SIGN_IN =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin=findViewById(R.id.Admin);

        BottomsheetdialogFragment addPhotoBottomDialogFragment =
                BottomsheetdialogFragment.newInstance();
        addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                BottomsheetdialogFragment.TAG);
//        getSupportActionBar().hide();
        register=findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bottomsheetdialog2 addPhotoBottomDialogFragment =
                        Bottomsheetdialog2.newInstance();
                addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                        Bottomsheetdialog2.TAG);

            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomsheetdialogFragment addPhotoBottomDialogFragment =
                        BottomsheetdialogFragment.newInstance();
                addPhotoBottomDialogFragment.show(getSupportFragmentManager(),
                        BottomsheetdialogFragment.TAG);


            }

        });





    }




    @Override
    public void onItemClick(String item) {
        textView.setText("Selected action item is " + item);
    }
}