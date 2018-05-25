package com.example.nchac.audito;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;

import java.nio.ByteBuffer;

public class MicrophoneServiceActivity extends Service implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String APP_TAG = "Microphone";
    private static final int mSampleRate = 44100;
    private static final int mFormat     = AudioFormat.ENCODING_PCM_16BIT;

    private AudioTrack mAudioOutput;
    private AudioRecord mAudioInput;
    private int                     mInBufferSize;
    public int                     mOutBufferSize;
    SharedPreferences mSharedPreferences;
    private static boolean          mActive = false;
    private MicrophoneReceiver      mBroadcastReceiver;


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // intercept the preference change.

        if (!key.equals("active"))
            return;

        boolean bActive = sharedPreferences.getBoolean("active", false);

        Log.d(APP_TAG, "Mic state changing (from " + mActive + " to " + bActive + ")");

        if (bActive != mActive) {

            mActive = bActive;

            if (mActive)
                record();
        }

    }

    private class MicrophoneReceiver extends BroadcastReceiver {
        // Turn the mic off when things get loud
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action != null && action.equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {

                SharedPreferences prefs = context.getSharedPreferences(APP_TAG, Context.MODE_PRIVATE);

                SharedPreferences.Editor e = prefs.edit();
                e.putBoolean("active", false);
                e.commit();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    public void onCreate() {

        Log.d(APP_TAG, "Creating mic service");


        mBroadcastReceiver = new MicrophoneReceiver();

        // create input and output streams
        mInBufferSize  = AudioRecord.getMinBufferSize(mSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO, mFormat);
        mOutBufferSize = AudioTrack.getMinBufferSize(mSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO, mFormat);
        mAudioInput = new AudioRecord(MediaRecorder.AudioSource.MIC, mSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO, mFormat, mInBufferSize);
        mAudioOutput = new AudioTrack(AudioManager.STREAM_MUSIC, mSampleRate, AudioFormat.CHANNEL_CONFIGURATION_MONO, mFormat, mOutBufferSize, AudioTrack.MODE_STREAM);

        // listen for preference changes
        mSharedPreferences = getSharedPreferences(APP_TAG, MODE_PRIVATE);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        mActive = mSharedPreferences.getBoolean("active", false);

        if (mActive)
            record();
    }
    @Override
    public void onDestroy() {
        Log.d(APP_TAG, "Stopping mic service");

        // close the service
        SharedPreferences.Editor e = mSharedPreferences.edit();
        e.putBoolean("active", false);
        e.commit();

        // disable the listener
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);

        mAudioInput.release();
        mAudioOutput.release();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(APP_TAG, "Service sent intent");

        // if this is a stop request, cancel the recording
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals("com.example.nikhil.new.STOP")) {
                Log.d(APP_TAG, "Cancelling recording via notification click");
                SharedPreferences.Editor e = mSharedPreferences.edit();
                e.putBoolean("active", false);
                e.commit();
            }
        }
    }

    public void record() {
        Thread t = new Thread() {
            public void run() {

                // allow the
                registerReceiver(mBroadcastReceiver, new IntentFilter(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY));

                Log.d(APP_TAG, "Entered record loop");

                recordLoop();

                Log.d(APP_TAG, "Record loop finished");
            }

            private void recordLoop() {
                if ( mAudioOutput.getState() != AudioTrack.STATE_INITIALIZED || mAudioInput.getState() != AudioTrack.STATE_INITIALIZED) {
                    Log.d(APP_TAG, "Can't start. Race condition?");
                }
                else {

                    try {

                        try { mAudioOutput.play(); }          catch (Exception e) { Log.e(APP_TAG, "Failed to start playback"); return; }
                        try { mAudioInput.startRecording(); } catch (Exception e) { Log.e(APP_TAG, "Failed to start recording"); mAudioOutput.stop(); return; }

                        try {

                            ByteBuffer bytes = ByteBuffer.allocateDirect(mInBufferSize);
                            int o = 0;
                            byte b[] = new byte[mInBufferSize];
                            while(mActive) {
                                o = mAudioInput.read(bytes, mInBufferSize);
                                bytes.get(b);
                                bytes.rewind();
                                mAudioOutput.write(b, 0, o);
                            }

                            Log.d(APP_TAG, "Finished recording");
                        }
                        catch (Exception e) {
                            Log.d(APP_TAG, "Error while recording, aborting.");
                        }

                        try { mAudioOutput.stop(); } catch (Exception e) { Log.e(APP_TAG, "Can't stop playback"); mAudioInput.stop(); return; }
                        try { mAudioInput.stop();  } catch (Exception e) { Log.e(APP_TAG, "Can't stop recording"); return; }
                    }
                    catch (Exception e) {
                        Log.d(APP_TAG, "Error somewhere in record loop.");
                    }
                }
                try {
                    unregisterReceiver(mBroadcastReceiver);
                } catch (IllegalArgumentException e) { Log.e(APP_TAG, "Receiver wasn't registered: " + e.toString()); }
            }
        };

        t.start();

    }


}
