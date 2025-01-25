package com.example.safetysphere;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Profile extends AppCompatActivity {
    TextView t1;
    Button b1;
    Button btn;
    int counter = 0;
    int y,m,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        t1 = findViewById(R.id.text1);
        b1 = findViewById(R.id.button1);
        btn=findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlertDialog();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    private void openAlertDialog() {
        // List of items to display in the AlertDialog
        final CharSequence[] items = {"Login", "MainActivity"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an item")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle item click, you can show a toast or perform another action
                        String selectedItem = items[which].toString();
                        //Toast.makeText(Profile.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                        Class<?> selectedClass = null;
                        try {
                            selectedClass = Class.forName("com.example.safety_sphere." + selectedItem);
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        Intent intent = new Intent(getApplicationContext(), selectedClass);
                        startActivity(intent);
                    }
                })
                .setPositiveButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Positive button action, you can perform another action here
                        //Toast.makeText(Profile.this, "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Negative button action, you can perform another action here
                        //Toast.makeText(Profile.this, "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    /*
    @Override
    public void onBackPressed() {
        final CharSequence[] items = {"Item 1", "Item 2", "Item 3"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an item")
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle item click, you can show a toast or perform another action
                        String selectedItem = items[which].toString();
                        Toast.makeText(Profile.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("Positive Button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Positive button action, you can perform another action here
                        finish();

                    }
                })
                .setNegativeButton("Negative Button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Negative button action, you can perform another action here
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        super.onBackPressed();
    }
*/
        private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                y=year;
                m=month;
                d=day;
                prog();
            }
        }, 2024, 2, 21);
        dialog.show();
    }

    public void prog() {
        final ProgressBar pb = findViewById(R.id.pb);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if (counter == 100) {
                    t.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Update the TextView when progress is completed
                            t1.setText(String.valueOf(y)+"/"+String.valueOf(m)+"/"+String.valueOf(d));
                        }
                    });
                }
            }
        };
        t.schedule(tt, 0, 100);
    }
}
