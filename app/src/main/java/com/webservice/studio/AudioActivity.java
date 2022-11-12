package com.webservice.studio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AudioActivity extends AppCompatActivity {

    EditText number;
    Button guessBtn;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        final MediaPlayer sound = MediaPlayer.create(AudioActivity.this,R.raw.sound);
        number = (EditText) findViewById(R.id.number);
        guessBtn = (Button)findViewById(R.id.guessBtn);

        guessBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String randText = "";
                Random randNum = new Random();
                int random = randNum.nextInt(5)+1;
                int userChoice = Integer.parseInt(number.getText().toString().trim());

                if(userChoice <1 || userChoice >5)
                {

                    Toast.makeText(AudioActivity.this, "Please guess a number between 1 and 5", Toast.LENGTH_SHORT).show();
                }
                else if(userChoice == random)
                {
					/*
					If User worked out guessing the number correctly
					Crowd Cheering sound will start
					 */
                    if(sound.isPlaying())
                    {
                        sound.seekTo(0);
                    }
                    else
                    {
                        sound.start();
                    }
                    Toast.makeText(AudioActivity.this, "Congratulations, you guessed the number correctly", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AudioActivity.this, "Sorry, better luck next time!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}