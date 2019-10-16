package uk.co.iamenigma.videodemo;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView godView;
    public void onClick(View view) {

        godView.start();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        godView = (VideoView) findViewById(R.id.godView);

        godView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.god);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(godView);
        godView.setMediaController(mediaController);


    }
}
