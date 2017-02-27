package com.example.user1.beatmaker;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btn_record1,btn_record2, btn_play1, btn_play2;
    private MediaPlayer play;
    private MediaRecorder record;
    private String MyFile1 = "file";
    private File fileout1;

    private String MyFile2 = "file";
    private File fileout2;
    private SoundPool sp = new SoundPool(99, AudioManager.STREAM_MUSIC, 0);
    private EditText et1,et2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_record1 = (Button) findViewById(R.id.btn_record1);
        btn_record1.setText("record");

        btn_record2 = (Button) findViewById(R.id.btn_record2);
        btn_record2.setText("record");
        btn_play1 = (Button) findViewById(R.id.btn_play1);
        btn_play2 = (Button) findViewById(R.id.btn_play2);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        MyFile1 = "temprecord1.gpp3";//gpp3 is an easy media file type

        fileout1 = new File(MainActivity.this.getFilesDir(), MyFile1);
        ////////////////////////////////////////dsfg///////////////////////
        btn_record1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_record1.getText() == "record") {//if not already recording
                    if (fileout1 != null)
                        fileout1.delete();//deletes any file that exists at the location
                    if (record != null)
                        record.release();//resets the mediarecorder
                    record = new MediaRecorder();
                    record.setAudioSource(MediaRecorder.AudioSource.MIC);//sets audio source to the microphone
                    record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//sets format to match file type
                    record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    record.setOutputFile(fileout1.getPath());
                    try {
                        record.prepare();
                    } catch (Exception e) {
                    }
                    record.start();//start recording to file
                    btn_record1.setText("stop");
                } else {//if already recording
                    if (record != null) {
                        record.stop();//stop recording to file
                        record.release();
                        btn_record1.setText("record");
                    }
                }
            }
        });
        //////////////////////////////////////////dfsgdsf//////////////////////////
        MyFile2 = "temprecord2.gpp3";//gpp3 is an easy media file type

        fileout2 = new File(MainActivity.this.getFilesDir(), MyFile2);
        btn_record2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_record2.getText() == "record") {//if not already recording
                    if (fileout2 != null)
                        fileout2.delete();//deletes any file that exists at the location
                    if (record != null)
                        record.release();//resets the mediarecorder
                    record = new MediaRecorder();
                    record.setAudioSource(MediaRecorder.AudioSource.MIC);//sets audio source to the microphone
                    record.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//sets format to match file type
                    record.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    record.setOutputFile(fileout2.getPath());
                    try {
                        record.prepare();
                    } catch (Exception e) {
                    }
                    record.start();//start recording to file
                    btn_record2.setText("stop");
                } else {//if already recording
                    if (record != null) {
                        record.stop();//stop recording to file
                        record.release();
                        btn_record2.setText("record");
                    }
                }
            }
        });
        //////////////////////////////////////////////////sdghsdfg//////////////////////////
        btn_play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int loop1=Integer.parseInt(et1.getText().toString());
                int loop2=Integer.parseInt(et2.getText().toString());
                sp.load(fileout1.getPath(), 1);
                sp.load(fileout2.getPath(),1);
                sp.play(1, 0, 1, 1, 1, 1);
                sp.play(2,1,0,1,1,1);
                //sp.play(id, left, right, priority, loop, rate)
            }

            ;
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user1.beatmaker/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user1.beatmaker/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
