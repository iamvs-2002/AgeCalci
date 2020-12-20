package com.vabrodex.agecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView=(VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://"+getPackageName()+ "/"+R.raw.aa);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if(isFinishing()){
                    return;}
                    startActivity(new Intent(Video.this,Second.class));
                    finish();
            }
        });
        videoView.start();
    }
}
