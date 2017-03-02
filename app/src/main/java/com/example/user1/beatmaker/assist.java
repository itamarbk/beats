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
    public void record(File fileout, Button btn, MediaRecorder record) {
        if (btn.getText() == "record") {//if not already recording
            if (fileout != null)
                fileout.delete();//deletes any file that exists at the location
            if (record != null)
                record.release();//resets the mediarecorder
            record = new MediaRecorder();
            record.setAudioSource(MediaRecorder.AudioSource.MIC);//sets audio source to the microphone
            record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//sets format to match file type
            record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            record.setOutputFile(fileout.getPath());
            try {
                record.prepare();
            } catch (Exception e) {
            }
            record.start();//start recording to file
            btn.setText("stop");
        } else {//if already recording
            if (record != null) {
                record.stop();//stop recording to file
                record.release();
                btn.setText("record");
            }
        }
    }
}
