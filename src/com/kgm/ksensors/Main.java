package com.kgm.ksensors;

import android.app.Activity;
import android.graphics.Point;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by goga on 6/28/14.
 */
public class Main extends Activity {
    kViewBase base = null;

    public Main() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);

        base = new kViewBase(this);
        setContentView(base);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        //params.x = 10;
        //params.height = getWindowManager().getDefaultDisplay().getHeight() - 10;
        params.width = getWindowManager().getDefaultDisplay().getWidth() - 10;
        //params.y = 10;

        getWindow().setAttributes(params);
    }

    @Override
    protected void onStop() {
        Log.v("App:", "Stop");
        super.onStop();

        base.onStop();
        finish();
    }

    @Override
    protected void onPause() {
        Log.v("App:", "Stop");
        super.onPause();

        base.onStop();
        finish();
    }
}
