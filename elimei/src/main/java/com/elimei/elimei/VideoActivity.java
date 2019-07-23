package com.elimei.elimei;

import android.app.ProgressDialog;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.elimei.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;


public class VideoActivity
        extends AppCompatActivity {
    private String newFileName;

    protected void onCreate(Bundle savedInstanceState) {
        /* 21 */
        super.onCreate(savedInstanceState);
        /* 22 */
        setContentView(R.layout.activity_video2);
        /* 23 */
        VideoView videoView = (VideoView) findViewById(R.id.videoviews);
        /* 24 */
        copyFile("video.mp4");
        /* 25 */
        File file = new File(this.newFileName);
        /* 26 */
        videoView.setVideoURI(Uri.fromFile(file));
        /* 27 */
        MediaController mediaController = new MediaController(this);
        /* 28 */
        videoView.setMediaController(mediaController);
        /* 29 */
        videoView.start();
    }


    private void copyFile(String filename) {
        /* 34 */
        AssetManager assetManager = getAssets();
        /* 35 */
        InputStream in = null;
        /* 36 */
        OutputStream out = null;
        try {
            /* 38 */
            in = assetManager.open(filename);
            /* 39 */
            this.newFileName = "/data/data/" + getPackageName() + "/" + filename;
            /* 40 */
            File file = new File(this.newFileName);
            /* 41 */
            if (file.exists()) {
                return;
            }
            /* 44 */
            out = new FileOutputStream(this.newFileName);
            /* 45 */
            byte[] buffer = new byte[1024];
            int read;
            /* 47 */
            while ((read = in.read(buffer)) != -1) {
                /* 48 */
                out.write(buffer, 0, read);
            }
            /* 50 */
            in.close();
            /* 51 */
            in = null;
            /* 52 */
            out.flush();
            /* 53 */
            out.close();
            /* 54 */
            out = null;
            /* 55 */
        } catch (Exception e) {
            /* 56 */
            e.printStackTrace();
        }
    }


}
