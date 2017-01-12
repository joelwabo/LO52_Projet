package com.example.joelw.multimediaserver.View;

import android.content.Intent;
import android.widget.MediaController;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.joelw.multimediaserver.Model.Media;
import com.example.joelw.multimediaserver.R;


public class MovieActivity extends AppCompatActivity {
    private VideoView video;
    public TextView movieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);


        Intent intent = getIntent();
        Media media = intent.getParcelableExtra("media");

        video = (VideoView) findViewById(R.id.videoView);
        movieName = (TextView) findViewById(R.id.movieName);
        movieName.setText(media.name);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            video.setMediaController(new MediaController(MovieActivity.this));
        }
        video.setVideoURI(Uri.parse(media.path));
    }

}
