package com.example.user1.beatmaker;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;

/**
 * Created by USER1 on 20/03/2017.
 */
public class loopService extends Service {
    File fileout;
    final SoundPool sp = new SoundPool(99, AudioManager.STREAM_MUSIC, 0);
    int id=0;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        id = sp.load(fileout.getPath(), 1);
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                sp.setLoop(id,1);
                //soundpool.play(id,leftV,rightV,priority,loop,rate)
            }
        });

    }

    @Override
    public int onStartCommand(Intent intent,int flags, int startId) {
        super.onStart(intent, startId);
        Intent fileIntent=intent;
        fileout=(File) fileIntent.getExtras().get("file");
        Log.d("file", fileout+"");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
