package com.example.hakim.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private EditText nama,alamat, tgl;
    private Spinner jk, agama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);



        nama = (EditText)findViewById(R.id.nama);
        alamat = (EditText)findViewById(R.id.alamat);
        tgl = (EditText)findViewById(R.id.tgl);
        jk = (Spinner)findViewById(R.id.jk);
        agama = (Spinner)findViewById(R.id.agama);

        btnSubmit = (Button)findViewById(R.id.btnRegist);
        btnSubmit.setOnClickListener(this);
        tgl.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.jk:

                break;
            case R.id.btnRegist:
                String namaU = nama.getText().toString();
                String alamatU = alamat.getText().toString();
                String tglU = tgl.getText().toString();
                String jku = jk.getSelectedItem().toString();
                String agamaU = agama.getSelectedItem().toString();

                boolean isEmptyFields = true;

                if (TextUtils.isEmpty(namaU)){
                    isEmptyFields = false;
                    nama.setError("Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(alamatU)){
                    isEmptyFields = false;
                    tgl.setError("Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(tglU)){
                    isEmptyFields = false;
                    nama.setError("Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(jku)){
                    isEmptyFields = false;
                    tgl.setError("Tidak Boleh Kosong");
                }
                if (TextUtils.isEmpty(agamaU)){
                    isEmptyFields = false;
                    nama.setError("Tidak Boleh Kosong");
                }


                if (isEmptyFields){
                    String type = "register";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, namaU, alamatU, tglU, jku, agamaU);
                }
                break;
        }
    }
}
