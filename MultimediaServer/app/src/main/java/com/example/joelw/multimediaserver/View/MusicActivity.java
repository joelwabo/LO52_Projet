package com.example.joelw.multimediaserver.View;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.joelw.multimediaserver.Model.Media;
import com.example.joelw.multimediaserver.R;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    public TextView songName, duration;
    private double timeElapsed = 0, finalTime = 0;
    private int forwardTime = 2000, backwardTime = 2000;
    private Handler durationHandler = new Handler();
    private SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent intent = getIntent();
        Media media = intent.getParcelableExtra("media");

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(media.path));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finalTime = mediaPlayer.getDuration();

        songName = (TextView) findViewById(R.id.songName);
        duration = (TextView) findViewById(R.id.songDuration);
        seekbar = (SeekBar) findViewById(R.id.seekBar);

        songName.setText(media.name);
        seekbar.setMax((int) finalTime);
        seekbar.setClickable(false);
    }

    public void play(View view) {
        mediaPlayer.start();
        timeElapsed = mediaPlayer.getCurrentPosition();
        seekbar.setProgress((int) timeElapsed);
        durationHandler.postDelayed(updateSeekBarTime, 100);
    }

    // Use to change seekBarTime
    private Runnable updateSeekBarTime = new Runnable() {
        public void run() {
            timeElapsed = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) timeElapsed);
            double timeRemaining = finalTime - timeElapsed;
            duration.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining),
                    TimeUnit.MILLISECONDS.toSeconds((long) timeRemaining) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) timeRemaining))));

            durationHandler.postDelayed(this, 100);
        }
    };

    public void pause(View view) {
        mediaPlayer.pause();
    }

    public void forward(View view) {
        //check if we can go forward at forwardTime seconds before song ended
        if ((timeElapsed + forwardTime) <= finalTime) {
            timeElapsed = timeElapsed + forwardTime;

            //seek to the exact second of the track
            mediaPlayer.seekTo((int) timeElapsed);
        }
    }

    public void rewind(View view) {
        //check if we can go back at backwardTime seconds after song starts
        if ((timeElapsed - backwardTime) > 0) {
            timeElapsed = timeElapsed - backwardTime;

            //seek to the exact second of the track
            mediaPlayer.seekTo((int) timeElapsed);
        }
    }

    /*@Override
    protected void onStop() {
        mediaPlayer.pause();
    }*/
}
