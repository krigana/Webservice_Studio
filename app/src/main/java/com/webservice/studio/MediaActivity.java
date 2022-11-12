package com.webservice.studio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Locale;

public class MediaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        Locale locale = new Locale("uk");
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // We have 2 different activities to Handle via Menus
        int id = item.getItemId();
        if(id==R.id.menu_game)
        {
            Intent audioIntent = new Intent(MediaActivity.this,AudioActivity.class);
            startActivity(audioIntent);
        }
        else if(id==R.id.menu_pictures)
        {
            Intent pictureIntent = new Intent(MediaActivity.this, PictureActivity.class);
            startActivity(pictureIntent);
        }
        return true;
    }}