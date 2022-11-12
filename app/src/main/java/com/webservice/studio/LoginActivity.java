package com.webservice.studio;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity  {

    EditText formEmail;
    EditText formPassword;
    TextView register;
    TextView did_not_enter_email;
    Button loginBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        formEmail = (EditText) findViewById(R.id.txt_email);
        formPassword = (EditText) findViewById(R.id.txt_password);
        register = (TextView) findViewById(R.id.view_register);
        loginBtn = (Button) findViewById(R.id.btn_login);


        Locale locale = new Locale("uk");
        Configuration config = getBaseContext().getResources().getConfiguration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent registerIntent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }


        });

        loginBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){

                String email = formEmail.getText().toString().trim();
                String password = formPassword.getText().toString().trim();

                if (email.matches("")) {
                    Toast.makeText(LoginActivity.this, "You did not enter a email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.matches("")) {
                    Toast.makeText(LoginActivity.this, "You did not enter a password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.isUserRegistered(email,password))

                {
                    // We can create an Intent and go to another activity or screen, else show a Toast Message
                    Intent moveToWelcomePage = new Intent(LoginActivity.this,MainActivity.class);

                    startActivity(moveToWelcomePage);
                    Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "User does not Exists", Toast.LENGTH_SHORT).show();
                }

            }

        });



    }
}



