package com.example.safetysphere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Sos extends AppCompatActivity {
    ImageButton ib;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        ib=findViewById(R.id.imageButton);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.UPSIDE_DOWN_CAKE){
            if(ContextCompat.checkSelfPermission(Sos.this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Sos.this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String channelID="CHANNEL_ID_NOTIFICATION";
                NotificationCompat.Builder builder=
                        new NotificationCompat.Builder(getApplicationContext(),channelID);
                builder.setSmallIcon(R.drawable.baseline_360_24)
                        .setContentTitle("Alert")
                        .setContentText("Alert Send to Parents")
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("data","Contact these People");
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,PendingIntent.FLAG_MUTABLE);
                builder.setContentIntent(pendingIntent);
                NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel=
                            notificationManager.getNotificationChannel(channelID);
                    if(notificationChannel==null){
                        int importance=NotificationManager.IMPORTANCE_HIGH;
                        notificationChannel=new NotificationChannel(channelID,"Some Description",importance);
                        notificationChannel.setLightColor(Color.GREEN);
                        notificationChannel.enableVibration(true);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                }
                notificationManager.notify(0, builder.build());
            }
        });
    }
}