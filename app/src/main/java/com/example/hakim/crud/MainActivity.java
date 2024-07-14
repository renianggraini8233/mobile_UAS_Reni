package com.example.hakim.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText inputUser, inputPass;
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputUser = (EditText)findViewById(R.id.username);
        inputPass = (EditText)findViewById(R.id.password);

        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:

//                String inputU = inputUser.getText().toString().trim();
//                String inputP = inputPass.getText().toString().trim();

                String user = inputUser.getText().toString();
                String pass = inputPass.getText().toString();

                boolean isEmptyFields = true;

                if (TextUtils.isEmpty(user)){
                    isEmptyFields = false;
                    inputUser.setError("Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(pass)){
                    isEmptyFields = false;
                    inputPass.setError("Tidak Boleh Kosong");
                }

                if (isEmptyFields){
                    String type = "login";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, user, pass);
                }
                break;
        }
    }
}
