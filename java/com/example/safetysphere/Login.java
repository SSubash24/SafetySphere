package com.example.safetysphere;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView t;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t=(TextView) findViewById(R.id.t2);
        t.setText("Login");
        // Assuming you have a button with an ID "myButton" in your layout
        btn=findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu pop=new PopupMenu(Login.this,btn);
                pop.getMenuInflater().inflate(R.menu.pop_up,pop.getMenu());
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
                pop.show();

            }
        });


        ImageView logoImageView = findViewById(R.id.logo);

        // Set OnClickListener for the ImageView
        logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a Toast message when the ImageView is clicked
                showToast("ImageView Clicked!");
            }
        });
    }

    private void openGoogleSignInPage() {
        // URL for Google Sign-In page
        String googleSignInUrl = "https://google.com";

        // Create an implicit intent to open the URL in a browser
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleSignInUrl));

        // Check if there's a browser app to handle the intent
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            // Handle the case where no browser app is available
            // You can show a message or take alternative action

        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}