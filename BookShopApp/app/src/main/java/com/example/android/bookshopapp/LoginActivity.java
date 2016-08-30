package com.example.android.bookshopapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bookshopapp.model.UserManager;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username= (EditText) findViewById(R.id.editTxtUsernameLog);
        password = (EditText) findViewById(R.id.editTxtPasswordLog);
        login = (Button) findViewById(R.id.buttonLogin);
        register = (Button) findViewById(R.id.btnRegister);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginCheck = UserManager.getInstance().isLogin(username.getText().toString(),password.getText().toString());
                if(!loginCheck) {
                    Toast.makeText(LoginActivity.this, "Sorry, user with that username and password doesn't exist!", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                    return;
                }
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent2);
            }
        });




    }
}
