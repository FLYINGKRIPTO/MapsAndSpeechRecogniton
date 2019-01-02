package com.example.dell.udemyapp79mapsandspeech;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
 private static  final int SPEAK_REQUEST = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //we need to find if users device actually supports speech recognition
        PackageManager packageManager = this.getPackageManager();
        List<ResolveInfo> listOfInformation = packageManager.queryIntentActivities(
                new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH),0
        );


        if(listOfInformation.size()>0){
            //this means users device does supports speech recognition
            Toast.makeText(MainActivity.this,
                    "Your device  supports speech recognition",Toast.LENGTH_SHORT).show();

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
}
