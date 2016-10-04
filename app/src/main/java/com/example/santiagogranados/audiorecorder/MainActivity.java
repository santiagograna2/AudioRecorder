 package com.example.santiagogranados.audiorecorder;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

 public class MainActivity extends AppCompatActivity {

    MediaRecorder myAudioRecorder;
    private String outputFile = null;
    private Button grabar, parar, reproducir;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grabar = (Button) findViewById(R.id.button1);
        parar = (Button) findViewById(R.id.button2);
        reproducir = (Button) findViewById(R.id.button3);
        imageView = (ImageView) findViewById(R.id.imageView);


        parar.setEnabled(false);
        reproducir.setEnabled(false);
        outputFile = Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/myrec.3gp";

        myAudioRecorder = new MediaRecorder();
        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        myAudioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        myAudioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        myAudioRecorder.setOutputFile(outputFile);


    }

     public void grabar(View v) {

         try {
             myAudioRecorder.prepare();
             myAudioRecorder.start();
         }catch (IllegalStateException e){
             e.printStackTrace();

         }catch (IOException e) {
             e.printStackTrace();
         }
         imageView.setImageResource(R.drawable.rec);
         grabar.setEnabled(false);
         parar.setEnabled(true);

         Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();


     }
     public void parar (View v) {
         myAudioRecorder.stop();
         myAudioRecorder.release();
         myAudioRecorder = null;
         parar.setEnabled(false);
         reproducir.setEnabled(true);
         imageView.setVisibility(View.INVISIBLE);
         Toast.makeText(this, "Grabacion realizada", Toast.LENGTH_SHORT).show();

     }
     public void reproducir (View v) throws IOException {
         MediaPlayer m = new MediaPlayer();
         imageView.setImageResource(R.drawable.playy);
         imageView.setVisibility(View.VISIBLE);
         m.setDataSource(outputFile);
         m.prepare();
         m.start();
         Toast.makeText(this, "Reproduciendo...", Toast.LENGTH_SHORT).show();

     }




}
