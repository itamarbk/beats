package com.example.user1.beatmaker;

import android.media.MediaRecorder;
import android.widget.Button;

import java.io.File;

/**
 * Created by USER1 on 02/03/2017.
 */
public class assist {
    public static void waitFor(final int millisec){
        Thread thread=  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        wait(millisec);
                    }
                }
                catch(InterruptedException ex){
                }

                // TODO
            }
        };

        thread.start();
    }

}
