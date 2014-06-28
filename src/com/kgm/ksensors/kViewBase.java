package com.kgm.ksensors;

import android.content.Context;
import android.graphics.Color;
import android.os.RemoteException;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.System;

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
                try
                {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            android.provider.Settings.System.SCREEN_BRIGHTNESS, progress);
                }
                catch(Exception e)
                {

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

        seeker.setMax(255);

        try
        {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    Settings.System.VOLUME_MUSIC));
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
                }
                catch(Exception e)
                {

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
        tv.setText("Voice");
        addView(tv);

        SeekBar seeker = new SeekBar(context);

        seeker.setMax(255);

        try
        {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    Settings.System.VOLUME_VOICE));
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
                            Settings.System.VOLUME_VOICE, progress);
                }
                catch(Exception e)
                {

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
}
