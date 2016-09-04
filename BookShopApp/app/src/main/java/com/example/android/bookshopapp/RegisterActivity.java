package com.example.android.bookshopapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.android.bookshopapp.model.UserManager;


public class RegisterActivity extends AppCompatActivity {
    private EditText password;
    private EditText username;
    private EditText name;
    private EditText email;
    private EditText passwordAgain;
    private EditText address;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        password = (EditText) findViewById(R.id.passwordAdd);
        username = (EditText) findViewById(R.id.usernameAdd);
        name = (EditText) findViewById(R.id.nameAdd);
        email = (EditText) findViewById(R.id.email);
        passwordAgain=(EditText)findViewById(R.id.repeatPassword);
        address = (EditText) findViewById(R.id.address);
        btnRegister = (Button) findViewById(R.id.btn_registerAdd);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UserManager.getInstance(RegisterActivity.this).existsUser(username.getText().toString())) {
                    username.setError("User with this name is exist");
                    username.requestFocus();
                    return;
                }

                if (username.getText().toString().length() > 10 || username.getText().toString().length() < 6) {
                    username.setError("Username must be less than 10 symbols and more than 6 symbols");
                    username.requestFocus();
                    return;
                }


                if (name.getText().toString().isEmpty()) {
                    name.setError("Invalid name");
                    name.requestFocus();
                    return;
                }

                if (password.getText().toString().length() < 6) {
                    password.setError("Password must be more than 6 symbols.");
                    password.requestFocus();
                    return;
                }

                if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                    passwordAgain.setError("Password is not the same.");
                    passwordAgain.requestFocus();
                    return;
                }
                if (!UserManager.getInstance(RegisterActivity.this).isValidEmail(email.getText().toString())) {
                    email.setError("Invalid email");
                    email.requestFocus();
                    return;
                }
                if(address.getText().toString().isEmpty()){
                    address.setError("Please enter your address!");
                    address.requestFocus();
                    return;
                }

                UserManager.getInstance(RegisterActivity.this).userRegister(RegisterActivity.this,username.getText().toString(),name.getText().toString(),password.getText().toString(),email.getText().toString(),address.getText().toString());
                finish();
            }

        });
    }



}
