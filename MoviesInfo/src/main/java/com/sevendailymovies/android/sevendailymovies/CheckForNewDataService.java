package com.sevendailymovies.android.sevendailymovies;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CheckForNewDataService extends Service {
    public CheckForNewDataService() {

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
                        Thread.sleep(86400000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendBroadcast(new Intent("NewData"));
                }
            }
        }).start();

        return START_STICKY;
    }
}
