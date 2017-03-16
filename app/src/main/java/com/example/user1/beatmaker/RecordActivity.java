package com.example.user1.beatmaker;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaRecorder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class RecordActivity extends AppCompatActivity {
    private MediaRecorder record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        final String LOOP_EXPLAIN="poop on that loop";
        final Button btn = (Button) findViewById(R.id.btn_record);
        Button btn_done = (Button) findViewById(R.id.btn_done);
        Button btn_loop=(Button)findViewById(R.id.btn_loop);
        btn.setText("record");
        Intent stillMyIntent = getIntent();
        final File fileout = (File) stillMyIntent.getExtras().get("file");
        Log.d("the file is",fileout.getPath());
        /*                                       record                                         */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        return;
                    }
                }
            }
        });
    /*                                       go back                              */
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecordActivity.this, MainActivity.class));
            }
        });
        /*                                       looop                              */
        btn_loop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder loop=new AlertDialog.Builder(RecordActivity.this);
                loop.setTitle("loop poop");
                TextView text=new TextView(RecordActivity.this);
                text.setText(LOOP_EXPLAIN);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                text.setLayoutParams(lp);
                loop.setView(text);
                loop.setPositiveButton("loop that goodness", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                loop.setNegativeButton("cool cool, maybe another day", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });
    }
}
