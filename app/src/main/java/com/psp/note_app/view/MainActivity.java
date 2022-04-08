package com.psp.note_app.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import com.psp.note_app.R;
import com.psp.note_app.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private final String NOTIFICATION_CHANNEL = "note_noti_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(this);

        viewModel.getShowNotificationObserver().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    // show welcome notification
                    viewModel.isFirstTimeShowNotification(true);
                    showNotification();
                }
            }
        });

        viewModel.checkWelcomeNotification();
    }

    private void showNotification() {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL)
                .setContentTitle("Hola")
                .setContentText("Thank you for downloading Note-App")
                .setSmallIcon(R.drawable.ic_launcher_background);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(123, builder.build());
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // create notification channel
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL,"WelcomeNotification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

}