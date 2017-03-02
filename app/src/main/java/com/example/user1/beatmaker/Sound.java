package com.example.user1.beatmaker;

import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.widget.Button;

import java.io.File;

/**
 * Created by USER1 on 02/03/2017.
 */
public class Sound {
    private static MediaRecorder record;
    private static SoundPool sp = new SoundPool(99, AudioManager.STREAM_MUSIC, 0);
    private Button btn;
    private File fileout;



    public void play(){
        sp.load(fileout.getPath(), 1);
        sp.play(1, 1, 1, 1, 0, 1);
        //soundpool.play(id,leftV,rightV,priority,loop,rate)
    }

    public Sound(File fileout, Button btn) {
        this.fileout = fileout;
        this.btn = btn;
    }
}
