package com.example.nchac.audito;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class Text2Speech extends Activity {
    TextToSpeech tts;
    private EditText write;
    private TextView resultTEXT;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_2_speech);
        resultTEXT = (TextView)findViewById(R.id.textView);
        write = (EditText)findViewById(R.id.editText1);
        tts= new TextToSpeech(getApplicationContext(),
                new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status != TextToSpeech.ERROR) {
                            tts.setLanguage(Locale.ENGLISH);
                        }
                    }


                });
    }
    public void onButtonClick (View v)
    {
        if (v.getId() == R.id.imageButton)
        {

            promptSpeechInput();
        }
    }
    public void promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL , RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE , Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT , "SAY SOMETHING");

        try {

            startActivityForResult(i, 100);
        }
        catch(ActivityNotFoundException a)
        {
            Toast.makeText(Text2Speech.this , "SRY YOUR DEVICE DOESN'T SUPPORT THIS LANGUAGE" , Toast.LENGTH_LONG ).show();

        }

    }
    public void onActivityResult (int request_code , int result_code , Intent i)
    {
        super.onActivityResult(request_code, result_code, i);
        switch (request_code)
        {
            case 100: if (result_code == RESULT_OK && i!= null)

            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultTEXT.setText(result.get(0));
            }
                break;
        }
    }

    @Override
    public void onPause(){
        if(tts !=null){
            tts.stop();
            tts.shutdown();
        }
        super.onPause();

    }


    public void speakText(View view){
        String toSpeak = write.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak,
                Toast.LENGTH_SHORT).show();
        tts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null,null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id=item.getItemId();
        if(id==R.id.mic){
            startActivity(new Intent(Text2Speech.this, MainActivity.class));

            return true;
        }
        if(id==R.id.tts){
            return false;
        }
        if(id==R.id.stt){
            startActivity(new Intent(Text2Speech.this, Text2Speech.class));
            return true;
        }
        if(id==R.id.learn){
            startActivity(new Intent(Text2Speech.this, learn.class));

            return true;
        }
        return true;
    }


}
