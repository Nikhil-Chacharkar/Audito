package com.example.nchac.audito;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class learn extends AppCompatActivity {

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
            startActivity(new Intent(learn.this, MainActivity.class));

            return true;
        }
        if(id==R.id.tts){
            startActivity(new Intent(learn.this, Text2Speech.class));
            return true;
        }

        if(id==R.id.learn){
            return false;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn);
        final MediaPlayer mp = new MediaPlayer();
        Button b1 = (Button) findViewById(R.id.button1);
        Button b2 = (Button) findViewById(R.id.button2);
        Button b3 = (Button) findViewById(R.id.button3);
        Button b4 = (Button) findViewById(R.id.button4);
        Button b5 = (Button) findViewById(R.id.button5);
        Button b6 = (Button) findViewById(R.id.button6);
        Button b7 = (Button) findViewById(R.id.button7);
        Button b8 = (Button) findViewById(R.id.button8);
        Button b9 = (Button) findViewById(R.id.button9);
        Button b10 = (Button) findViewById(R.id.button10);
        Button b11 = (Button) findViewById(R.id.button11);
        Button b12 = (Button) findViewById(R.id.button12);




        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("seat.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("sit.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("foot.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("food.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("head.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("teacher.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("girl.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("walk.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("had.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("cup.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("heart.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
        b12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (mp.isPlaying()) {
                    mp.stop();
                }


                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("hot.ogg");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

}
