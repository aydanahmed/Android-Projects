package com.example.android.myapp;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button mButtonLogIn;
    private Button mButtonReg;
    private EditText usernameLog;
    private EditText enterPassword;
    private HashMap<String, String> users = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonLogIn = (Button) findViewById(R.id.button);
        mButtonReg = (Button) findViewById(R.id.button2);
        usernameLog = (EditText) findViewById(R.id.editText);
        enterPassword = (EditText) findViewById(R.id.editText2);
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameLog.getText().toString().isEmpty()){
                    usernameLog.setError("Enter username");
                    usernameLog.requestFocus();
                    return;
                }
                if(enterPassword.getText().toString().isEmpty()){
                    enterPassword.setError("Enter password");
                    enterPassword.requestFocus();
                    return;
                }
                if (users.containsKey(usernameLog.getText().toString()) && users.get(usernameLog.getText().toString()).equals(enterPassword.getText().toString())) {
                    Intent data2 = new Intent(MainActivity.this, HomeActivity.class);
                    data2.putExtra("Login", "Welcome to home page!");
                    startActivity(data2);
                } else {
                    usernameLog.requestFocus();
                    usernameLog.setText("");
                    enterPassword.setText("");
                    Toast.makeText(MainActivity.this, "Wrong data", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mButtonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                // set the request code to any code you like,
                // you can identify the callback via this code
                startActivityForResult(i, 1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.hasExtra("Username") && data.hasExtra("Password")) {
                users.put(data.getExtras().getString("Username"), data.getExtras().getString("Password"));
                Toast.makeText(this, data.getExtras().getString("Username") + " registered succesfully.",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }


}
