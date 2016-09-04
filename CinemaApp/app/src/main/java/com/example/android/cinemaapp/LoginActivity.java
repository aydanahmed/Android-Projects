package com.example.android.cinemaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.cinemaapp.Model.UserManager;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button buttonLog;
    private Button registerLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    username = (EditText) findViewById(R.id.usernameLog);
        password = (EditText) findViewById(R.id.passLog);
        buttonLog = (Button) findViewById(R.id.buttonLog);
    registerLog= (Button) findViewById(R.id.buttonReg);


        registerLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!UserManager.getInstance().loginCheck(username.getText().toString(),password.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Invalid data!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(LoginActivity.this,CinemaActivity.class);
                startActivity(intent);



            }
        });

    }



}
