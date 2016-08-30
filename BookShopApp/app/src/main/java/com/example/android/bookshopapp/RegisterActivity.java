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
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        password = (EditText) findViewById(R.id.passwordAdd);
        username= (EditText) findViewById(R.id.usernameAdd);
        name = (EditText) findViewById(R.id.nameAdd);
        email = (EditText) findViewById(R.id.email);
        passwordAgain=(EditText)findViewById(R.id.repeatPassword);
        btnRegister = (Button) findViewById(R.id.btn_registerAdd);
        UserManager.getInstance().getUserInfo();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UserManager.getInstance().getUserInfo().containsKey(username.getText().toString())){
                    username.setError("User with this name is exist");
                    return;
                }

                if (username.getText().toString().length() > 10 || username.getText().toString().length() < 6) {
                    username.setError("Username must be less than 10 symbols and more than 6 symbols");
                    return;
                }


                if (name.getText().toString().isEmpty()) {
                    name.setError("Invalid name");
                    return;
                }

                if (password.getText().toString().length() < 6) {
                    password.setError("Password must be more than 6 symbols.");
                    return;
                }

                if (!password.getText().toString().equals(passwordAgain.getText().toString())) {
                    passwordAgain.setError("Password is not the same.");
                    return;
                }
                if(!UserManager.getInstance().isValidEmail(email.getText().toString())){
                    email.setError("Invalid email");
                    return;
                }
                UserManager.getInstance().userRegister(name.getText().toString(),username.getText().toString(),password.getText().toString(),email.getText().toString());
                finish();
            }

        });
    }



}
