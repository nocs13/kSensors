package com.kgm.ksensors;

import android.app.Activity;
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

      base = new kViewBase(this);
      setContentView(base);
    }
}
