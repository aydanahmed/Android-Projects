package com.sevendailymovies.android.sevendailymovies;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

public class NotificationService extends Service {
    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(43200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendBroadcast(new Intent("MovieNotification"));
                }
            }
        }).start();
        return START_STICKY;
    }
}
