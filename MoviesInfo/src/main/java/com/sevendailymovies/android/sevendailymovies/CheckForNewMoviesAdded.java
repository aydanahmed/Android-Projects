package com.sevendailymovies.android.sevendailymovies;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class CheckForNewMoviesAdded extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(7200000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendBroadcast(new Intent("NewMoviesAdded"));
                }
            }
        }).start();
        return START_STICKY;
    }


}
