package com.yukang.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMonster;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.aa);
        btnMonster = (ImageButton) findViewById(R.id.imageButton);
        textView = (TextView) findViewById(R.id.textView);
        btnMonster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                textView.setText("Look Up!");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), (int) System.currentTimeMillis(), intent, 0);

                Notification notification = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    notification = new Notification.Builder(getApplicationContext()).setContentTitle("Hello! Test Note").setContentText("I'm a Monster").setContentIntent(pIntent).setSmallIcon(R.mipmap.mons32).build();
                }
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notification.flags = Notification.FLAG_AUTO_CANCEL;
                manager.notify(0, notification);
            }
        });
    }
}
