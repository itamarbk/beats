package com.example.user1.beatmaker;

import android.content.Intent;
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
    Sound[] sounds=new Sound[9];
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            //gpp3 is an easy media file type
            sounds[0] = new Sound(new File(MainActivity.this.getFilesDir(), "sound1.gpp3"), (Button) findViewById(R.id.btn1));
            sounds[1] = new Sound(new File(MainActivity.this.getFilesDir(), "sound2.gpp3"), (Button) findViewById(R.id.btn2));
            sounds[2] = new Sound(new File(MainActivity.this.getFilesDir(), "sound3.gpp3"), (Button) findViewById(R.id.btn3));
            sounds[3] = new Sound(new File(MainActivity.this.getFilesDir(), "sound4.gpp3"), (Button) findViewById(R.id.btn4));
            sounds[4] = new Sound(new File(MainActivity.this.getFilesDir(), "sound5.gpp3"), (Button) findViewById(R.id.btn5));
            sounds[5] = new Sound(new File(MainActivity.this.getFilesDir(), "sound6.gpp3"), (Button) findViewById(R.id.btn6));
            sounds[6] = new Sound(new File(MainActivity.this.getFilesDir(), "sound7.gpp3"), (Button) findViewById(R.id.btn7));
            sounds[7] = new Sound(new File(MainActivity.this.getFilesDir(), "sound8.gpp3"), (Button) findViewById(R.id.btn8));
            sounds[8] = new Sound(new File(MainActivity.this.getFilesDir(), "sound9.gpp3"), (Button) findViewById(R.id.btn9));
        }
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        for(final Sound sound : sounds) {
            sound.getBtn().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sound.play();
                }
            });
            sound.getBtn().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent myIntent=new Intent(MainActivity.this,RecordActivity.class);
                    myIntent.putExtra("file",sound.getFileout());
                    startActivity(myIntent);
                    return false;
                }
            });
        }
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
