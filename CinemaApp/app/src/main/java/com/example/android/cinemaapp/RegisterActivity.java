package com.example.android.cinemaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.android.cinemaapp.Model.UserManager;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameET;
    private EditText usernameET;
    private EditText passET;
    private EditText passRepeatET;
    private EditText ageET;
    private EditText emailET;
    private Button confirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameET = (EditText) findViewById(R.id.nameReg);
        usernameET = (EditText) findViewById(R.id.usernameReg);
        passET = (EditText) findViewById(R.id.passReg);
        passRepeatET = (EditText) findViewById(R.id.repeatPass);
        ageET = (EditText) findViewById(R.id.ageReg);
        emailET = (EditText) findViewById(R.id.emailReg);
        confirm = (Button) findViewById(R.id.confirm_button);
        final UserManager userManager = UserManager.getInstance();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nameET.getText().toString().isEmpty()){
                    nameET.setError("Please enter your name!");
                    nameET.requestFocus();
                    return;
                }
                if(usernameET.getText().toString().isEmpty()){
                    usernameET.setError("Please enter  username!");
                    usernameET.requestFocus();
                    return;
                }
                if(UserManager.getInstance().existUsername(usernameET.getText().toString())){
                    usernameET.setError("User with that username is exist! Please enter other username");
                    usernameET.requestFocus();
                    return;
                }
                if(passET.getText().toString().isEmpty()){
                    passET.setError("Please enter your password!");
                    passET.requestFocus();
                    return;
                } if(passRepeatET.getText().toString().isEmpty()) {
                    passRepeatET.setError("Please reenter your password!");
                    passRepeatET.requestFocus();
                    return;
                }
                if(!passRepeatET.getText().toString().equals(passET.getText().toString())){
                    passRepeatET.setError("Not correct! Reenter password!");
                    passRepeatET.requestFocus();
                    return;
                }

                if(ageET.getText().toString().isEmpty()){
                    ageET.setError("Please enter your age!");
                    ageET.requestFocus();
                    return;
                }


                if(emailET.getText().toString().isEmpty()){
                    emailET.setError("Please enter your email!");
                    emailET.requestFocus();
                    return;
                }

                userManager.register(nameET.getText().toString(),usernameET.getText().toString(),passET.getText().toString(),ageET.getText().toString(),emailET.getText().toString());
                finish();
            }
        });











    }
}
