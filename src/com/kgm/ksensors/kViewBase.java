package com.kgm.ksensors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.System;
import java.util.logging.Logger;

import static android.provider.Settings.*;

/**
 * Created by goga on 6/28/14.
 */
public class kViewBase extends LinearLayout
{
    public Context context = null;

    public kViewBase(final Context context)
    {
        super(context);

        //setBackgroundColor(0xaa000000);
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.TRANSPARENT);

        this.context = context;

        addBrightness(context);
        addMusic(context);
        addVoice(context);
    }

    void addBrightness(final Context context)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);

        TextView tv = new TextView(context);
        tv.setText("Brightness");
        addView(tv);

        SeekBar seeker = new SeekBar(context);

        seeker.setMax(255);

        try
        {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS));
        }
        catch(Exception e)
        {

        }

        addView(seeker);

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
            {
                if(progress < 1)
                    return;

                try
                {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            android.provider.Settings.System.SCREEN_BRIGHTNESS, progress);
                    WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
                    lp.screenBrightness = progress / (float)255;
                    ((Activity)context).getWindow().setAttributes(lp);
                }
                catch(Exception e)
                {
                    Log.v("Error", e.getMessage());
                }
            }
            public void onStartTrackingTouch(android.widget.SeekBar seekBar)
            {

            }

            public void onStopTrackingTouch(android.widget.SeekBar seekBar)
            {

            }
        });
    }

    void addMusic(final Context context)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);

        TextView tv = new TextView(context);
        tv.setText("Music");
        addView(tv);

        SeekBar seeker = new SeekBar(context);

        final AudioManager am = (AudioManager) ((Activity)context).getSystemService(Activity.AUDIO_SERVICE);

        seeker.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        try
        {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    Settings.System.VOLUME_MUSIC));
            seeker.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        }
        catch(Exception e)
        {

        }

        addView(seeker);

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
            {
                try
                {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            Settings.System.VOLUME_MUSIC, progress);
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }
                catch(Exception e)
                {
                  Log.v("Error", e.getMessage());
                }
            }
            public void onStartTrackingTouch(android.widget.SeekBar seekBar)
            {

            }

            public void onStopTrackingTouch(android.widget.SeekBar seekBar)
            {

            }
        });
    }

    void addVoice(final Context context)
    {
      LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
          LinearLayout.LayoutParams.WRAP_CONTENT);
      lp.setMargins(20, 20, 20, 20);

      TextView tv = new TextView(context);
      tv.setText("Call Voice");
      addView(tv);

      SeekBar seeker = new SeekBar(context);

      final AudioManager am = (AudioManager) ((Activity)context).getSystemService(Activity.AUDIO_SERVICE);

      seeker.setMax(am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));

      try
      {
        seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
            Settings.System.VOLUME_MUSIC));
        seeker.setProgress(am.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
      }
      catch(Exception e)
      {

      }

      addView(seeker);

      seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
        public void onProgressChanged (SeekBar seekBar, int progress, boolean fromUser)
        {
          try
          {
            android.provider.Settings.System.putInt(context.getContentResolver(),
                Settings.System.VOLUME_MUSIC, progress);
            am.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, 0);
          }
          catch(Exception e)
          {
            Log.v("Error", e.getMessage());
          }
        }
        public void onStartTrackingTouch(android.widget.SeekBar seekBar)
        {

        }

        public void onStopTrackingTouch(android.widget.SeekBar seekBar)
        {

        }
      });
    }

    public void draw_xxx(Canvas canvas)
    {
      canvas.saveLayerAlpha(null, 10, Canvas.ALL_SAVE_FLAG);
      super.draw(canvas);
      //canvas.restore();
    }
}
