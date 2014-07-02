package com.kgm.ksensors;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

import java.lang.System;
import java.util.logging.Logger;

import static android.provider.Settings.*;

/**
 * Created by goga on 6/28/14.
 */
public class kViewBase extends LinearLayout {
    public Context context = null;

    Camera camera = null;

    public kViewBase(final Context context) {
        super(context);

        //setBackgroundColor(0xaa000000);
        setOrientation(LinearLayout.VERTICAL);
        setBackgroundColor(Color.TRANSPARENT);

        this.context = context;

        addBrightness(context);
        addMusic(context);
        addVoice(context);
        addWifi(context);
        addBluetooth(context);
        addTorch(context);
    }

    public void onStop() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    void addBrightness(final Context context) {
        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);

        addView(ll);

        TextView tv = new TextView(context);
        tv.setText("Brightness");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        SeekBar seeker = new SeekBar(context);

        seeker.setMax(255);

        ll.addView(seeker);

        seeker.setLayoutParams(lp);

        try {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    android.provider.Settings.System.SCREEN_BRIGHTNESS));
        } catch (Exception e) {

        }

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress < 1)
                    return;

                try {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            android.provider.Settings.System.SCREEN_BRIGHTNESS, progress);
                    WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
                    lp.screenBrightness = progress / (float) 255;
                    ((Activity) context).getWindow().setAttributes(lp);
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }

            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }

    void addMusic(final Context context) {
        final AudioManager am = (AudioManager) ((Activity) context).getSystemService(Activity.AUDIO_SERVICE);

        if (am == null)
            return;

        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);

        addView(ll);

        TextView tv = new TextView(context);
        tv.setText("Music");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        SeekBar seeker = new SeekBar(context);

        seeker.setMax(am.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        seeker.setLayoutParams(lp);

        ll.addView(seeker);

        try {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    Settings.System.VOLUME_MUSIC));
            seeker.setProgress(am.getStreamVolume(AudioManager.STREAM_MUSIC));
        } catch (Exception e) {

        }

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            Settings.System.VOLUME_MUSIC, progress);
                    am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }

            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }

    void addVoice(final Context context) {
        final AudioManager am = (AudioManager) ((Activity) context).getSystemService(Activity.AUDIO_SERVICE);

        if (am == null)
            return;

        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);

        addView(ll);

        TextView tv = new TextView(context);
        tv.setText("Call Voice");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        SeekBar seeker = new SeekBar(context);

        seeker.setMax(am.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));

        seeker.setLayoutParams(lp);

        ll.addView(seeker);

        try {
            seeker.setProgress(android.provider.Settings.System.getInt(context.getContentResolver(),
                    Settings.System.VOLUME_MUSIC));
            seeker.setProgress(am.getStreamVolume(AudioManager.STREAM_VOICE_CALL));
        } catch (Exception e) {

        }

        seeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                try {
                    android.provider.Settings.System.putInt(context.getContentResolver(),
                            Settings.System.VOLUME_MUSIC, progress);
                    am.setStreamVolume(AudioManager.STREAM_VOICE_CALL, progress, 0);
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }

            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {

            }

            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {

            }
        });
    }

    void addWifi(final Context context) {
        final WifiManager wm = (WifiManager) ((Activity) context).getSystemService(Activity.WIFI_SERVICE);

        if (wm == null)
            return;

        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 0, 20, 0);

        addView(ll);

        TextView tv = new TextView(context);
        tv.setText("Wifi");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        CheckBox checker = new CheckBox(context);

        //checker.setLayoutParams(lp);

        ll.addView(checker);

        try {
            checker.setChecked(wm.isWifiEnabled());
        } catch (Exception e) {
            Log.v("Error", e.getMessage());
        }

        checker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    wm.setWifiEnabled(isChecked);
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }
        });
    }

    void addBluetooth(final Context context) {
        final BluetoothAdapter ba = (BluetoothAdapter) BluetoothAdapter.getDefaultAdapter();

        if (ba == null)
            return;

        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LayoutParams.WRAP_CONTENT);

        lp.setMargins(20, 0, 20, 0);

        addView(ll);

        TextView tv = new TextView(context);
        tv.setText("Bluetooth");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        CheckBox checker = new CheckBox(context);

        //checker.setLayoutParams(lp);

        ll.addView(checker);

        try {
            checker.setChecked(ba.isEnabled());
        } catch (Exception e) {
            Log.v("Error", e.getMessage());
        }

        checker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    if (isChecked)
                        ba.enable();
                    else
                        ba.disable();
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }
        });
    }

    void addTorch(final Context context) {
        boolean hasTorch = context.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (hasTorch == false)
            return;

        final Camera camera = Camera.open();

        if (camera == null)
            return;

        this.camera = camera;

        LinearLayout ll = new LinearLayout(context);

        ll.setOrientation(HORIZONTAL);

        addView(ll);


        TextView tv = new TextView(context);
        tv.setText("Flashlight");
        tv.setMinimumWidth(100);
        ll.addView(tv);

        CheckBox checker = new CheckBox(context);

        checker.setChecked(false);

        ll.addView(checker);

        final boolean isFlashOn = false;

        checker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    Camera.Parameters p = camera.getParameters();

                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                    if (isChecked) {
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                        camera.setParameters(p);
                    } else {
                        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                        camera.setParameters(p);
                    }
                } catch (Exception e) {
                    Log.v("Error", e.getMessage());
                }
            }
        });
    }

    public void draw_xxx(Canvas canvas) {
        canvas.saveLayerAlpha(null, 10, Canvas.ALL_SAVE_FLAG);
        super.draw(canvas);
        //canvas.restore();
    }
}
