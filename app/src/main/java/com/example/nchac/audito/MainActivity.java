package com.example.nchac.audito;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener {

    private static final String APP_TAG  ="Microphone";

    SharedPreferences mSharedPrefereences;
    boolean    mActive = false;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id=item.getItemId();
        if(id==R.id.mic){

            return false;
        }
        if(id==R.id.tts){
            startActivity(new Intent(MainActivity.this, Text2Speech.class));
            return true;
        }

        if(id==R.id.learn){
            startActivity(new Intent(MainActivity.this, learn.class));
            return true;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(APP_TAG, "Opening mic activity");

        mSharedPrefereences = getSharedPreferences(APP_TAG, MODE_PRIVATE);
        mSharedPrefereences.registerOnSharedPreferenceChangeListener(this);

        mActive = mSharedPrefereences.getBoolean("active", false);
        if(mActive)
            startService(new Intent(this, MicrophoneServiceActivity.class));

        setContentView(R.layout.activity_main);
        ImageButton b = (ImageButton)findViewById(R.id.RecordButton);
        b.setOnClickListener(this);
        b.setImageBitmap(BitmapFactory.decodeResource(getResources(), mActive ? R.drawable.red : R.drawable.mic));

        int lastVersion = mSharedPrefereences.getInt("lastVersion", 0);
        int thisVersion = -1;
        try {
            thisVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (lastVersion != thisVersion) {
            SharedPreferences.Editor e = mSharedPrefereences.edit();
            e.putInt("lastVersion", thisVersion);
            e.commit();
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(APP_TAG, "Closing mic activity");

        mSharedPrefereences.unregisterOnSharedPreferenceChangeListener(this);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.RecordButton) {
            SharedPreferences.Editor e = mSharedPrefereences.edit();
            e.putBoolean("active", !mActive);
            e.commit();
        }
    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // intercept the preference change.

        if (key.equals("active")) {
            boolean bActive = sharedPreferences.getBoolean("active", false);

            if (bActive != mActive) {
                if (bActive) {
                    startService(new Intent(this, MicrophoneServiceActivity.class));
                }
                else {
                    stopService(new Intent(this, MicrophoneServiceActivity.class));
                }
                mActive = bActive;
                runOnUiThread(	new Runnable() {
                    public void run() {
                        ImageButton b = (ImageButton)findViewById(R.id.RecordButton);
                        b.setImageBitmap(BitmapFactory.decodeResource(getResources(), mActive ? R.drawable.red : R.drawable.mic));
                    }
                });
            }
        }

    }

}

