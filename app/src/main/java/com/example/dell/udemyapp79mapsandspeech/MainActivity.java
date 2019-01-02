package com.example.dell.udemyapp79mapsandspeech;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
 private static  final int SPEAK_REQUEST = 10;
 TextView txt_value;
 Button btn_voice_intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_value = (TextView) findViewById(R.id.txtValue);
        btn_voice_intent = (Button) findViewById(R.id.btnVoiceIntent);

        btn_voice_intent.setOnClickListener(MainActivity.this);

        //we need to find if users device actually supports speech recognition
        PackageManager packageManager = this.getPackageManager();
        List<ResolveInfo> listOfInformation = packageManager.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0
        );


        if(listOfInformation.size()>0){
            //this means users device does supports speech recognition
            Toast.makeText(MainActivity.this,
                    "Your device  supports speech recognition",Toast.LENGTH_SHORT).show();
             //call listenToUserVoice method
            listenToUsersVoice();
        }
        else{
            Toast.makeText(MainActivity.this,
                    "Your device does not supports speech recognition",Toast.LENGTH_SHORT).show();
        }
        //Package Manager - class for retrieving various kinds of information related to the
        //application package that are currently installed on the device.
        //queryIntentActivities -- Retrieve all activities that can be performed for the given intent

                //ResolveInfo - information that is returned from resolving an intent
        //against an intent filter. This partially corresponds to information collected
        //from AndroidManifest.xml's <intent> tag

        //Recognizer Intent - android.speech.recognizer intent
         // it is a class that extends an object class
        //Constants for supporting speech recognition through starting an intent
       // ActionRecognizeSpeech - starts an activity that will prompt user for speech and
        // send it through speech recognizer


    }
    //Let's create a method in order to access speech recognition framework
    private void listenToUsersVoice(){
        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Talk to me");
        //user can speak in any language
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
         //specifying maximum results that we want to get from user's speech
        voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,10);
        startActivityForResult(voiceIntent,SPEAK_REQUEST);
    }
// override this method to get data from voice intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==SPEAK_REQUEST && resultCode == RESULT_OK){
           //We are going to get the words that the user has actually said
            //and we are going to assign those words to tha arraylist here which
            //is of type string

            ArrayList<String> voiceWords = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
           //We can also get a decimal value from the intent
            // this decimal value will be the preciseness of the words from the users voice
            float[] confidLevel = data.getFloatArrayExtra(
                    RecognizerIntent.EXTRA_CONFIDENCE_SCORES);
            int index = 0;
             for(String userWords : voiceWords){
                 if(confidLevel != null && index< confidLevel.length){
                     txt_value.setText(userWords + " - " +confidLevel[index]);
                 }
             }
        }
    }

    @Override
    public void onClick(View v) {
        listenToUsersVoice();
    }
}
