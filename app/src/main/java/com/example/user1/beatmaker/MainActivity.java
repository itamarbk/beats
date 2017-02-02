package com.example.user1.beatmaker;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btn_record, btn_play;
    private MediaPlayer play;
    private MediaRecorder record;
    private String MyFile="file";
    private File fileout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_record=(Button)findViewById(R.id.btn_record);
        btn_record.setText("record");
        btn_play=(Button)findViewById(R.id.btn_play);
        MyFile= "temprecord.gpp3";//gpp3 is an easy media file type

        fileout=new File(MainActivity.this.getFilesDir(),MyFile);
        btn_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_record.getText()=="record"){//if not already recording
                    if(fileout!=null)
                        fileout.delete();//deletes any file that exists at the location
                    if(record!=null)
                        record.release();//resets the mediarecorder
                    record=new MediaRecorder();
                    record.setAudioSource(MediaRecorder.AudioSource.MIC);//sets audio source to the microphone
                    record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//sets format to match file type
                    record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    record.setOutputFile(fileout.getPath());
                    try {
                        record.prepare();
                    }
                    catch(Exception e){}
                    record.start();//start recording to file
                    btn_record.setText("stop");
                }
                else{//if already recording
                    if(record!=null) {
                        record.stop();//stop recording to file
                        record.release();
                        btn_record.setText("record");
                    }
                }
            }
        });
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play=new MediaPlayer();
                try{
                play.setDataSource(fileout.getPath());//sets to play recorded file
                play.prepare();
                play.start();}//plays file
                catch(Exception e){}
                play.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//when done playing file
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        play.release();//release mediaplayer
                    }
                });
            }
        });
    }
}
