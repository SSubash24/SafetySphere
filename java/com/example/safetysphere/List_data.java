package com.example.safetysphere;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class List_data extends AppCompatActivity {
    ImageView img;
    TextView t1,t2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        img=findViewById(R.id.image);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        Intent intent=getIntent();
        img.setImageResource(intent.getIntExtra("img",0));
        t1.setText(intent.getStringExtra("text1"));
        t2.setText(intent.getStringExtra("text2"));

    }
}