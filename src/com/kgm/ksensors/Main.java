package com.kgm.ksensors;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;

/**
 * Created by goga on 6/28/14.
 */
public class Main extends Activity
{
    kViewBase base = null;

    public Main()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);

      setVolumeControlStream(AudioManager.STREAM_MUSIC);
      setVolumeControlStream(AudioManager.STREAM_VOICE_CALL);

      base = new kViewBase(this);
      setContentView(base);
    }
}
