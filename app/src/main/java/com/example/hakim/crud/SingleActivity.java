package com.example.hakim.crud;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class SingleActivity extends AppCompatActivity implements View.OnClickListener{
    public static String EXTRA_KEY = "extra_key";
    String id;
    private EditText namaedt, tgledt, alamatedt, jkedt, agamaedt;
    private Button btnEdt;
    private Button btnHps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);
        Integer iddb = getIntent().getIntExtra(EXTRA_KEY, 0);
        id = iddb.toString();
        namaedt = (EditText) findViewById(R.id.namaedt);
        tgledt = (EditText)findViewById(R.id.tgledt);
        alamatedt = (EditText)findViewById(R.id.alamatedt);
        jkedt = (EditText)findViewById(R.id.jkedt);
        agamaedt = (EditText)findViewById(R.id.agamaedt);
        btnEdt = (Button)findViewById(R.id.btnEdit);
        btnHps = (Button)findViewById(R.id.btnHapus);

        btnEdt.setOnClickListener(this);
        btnHps.setOnClickListener(this);

        HasilSendiri hasil = new HasilSendiri();
        hasil.execute();
    }

    private void changeToSetText(String result) {
        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                namaedt.setText(obj.getString("nama"));
                tgledt.setText(obj.getString("tgl_lahir"));
                jkedt.setText(obj.getString("jenis_kelamin"));
                alamatedt.setText(obj.getString("alamat"));
                agamaedt.setText(obj.getString("agama"));
                Log.d("nama1", obj.getString("nama"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEdit:
                String namaInput = namaedt.getText().toString();
                String tglInput = tgledt.getText().toString();
                String jkInput = jkedt.getText().toString();
                String alamatInput = alamatedt.getText().toString();
                String agamaInput = agamaedt.getText().toString();

                String type = "edit";
                BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                backgroundWorker.execute(type, namaInput, alamatInput, tglInput, jkInput, agamaInput , id);
                break;
            case R.id.btnHapus:
                String type1 = "hapus";
                BackgroundWorker backgroundWorker1 = new BackgroundWorker(this);
                backgroundWorker1.execute(type1, id);
                break;
        }
    }


    public class HasilSendiri extends AsyncTask<String, Void, String> {

        public HasilSendiri() {

        }
        protected void onPreExecute() {
            Log.d("ERORAJA", "INI DI DO IN PRE");
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String data_url = "http://192.168.43.146/androcrud/lihat1.php";
            String json = "";
            try {

                URL url = new URL(data_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);


                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line + "\n");
                }

                bufferedReader.close();
                return result.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("EROR", "INI DI POST");
            changeToSetText(result);
        }
    }


}

