package com.example.hakim.crud;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    FloatingActionButton btnfab;
    private AdapterList adapterList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnfab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView)findViewById(R.id.lview_item);
        btnfab.setOnClickListener(this);


        HasilData wow= new HasilData();
        wow.execute();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent i = new Intent(HomeActivity.this, RegistActivity.class);
                startActivity(i);
                break;
        }
    }
    private void loadIntoRecycleview(String result){
        try {
            ArrayList<Murid> muridArrayList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                Murid murid = new Murid();
                murid.setNama(obj.getString("nama"));
                murid.setTgl_lahir(obj.getString("tgl_lahir"));
                murid.setJenis_Kelamin(obj.getString("jenis_kelamin"));
                murid.setId(obj.getInt("id"));
                muridArrayList.add(murid);
                Log.d("nama", obj.getString("nama"));
            }
            adapterList = new AdapterList(muridArrayList, this);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HomeActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapterList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class HasilData extends AsyncTask<String, Void, String> {

        public HasilData() {

        }
        protected void onPreExecute() {
            Log.d("ERORAJA", "INI DI DO IN PRE");
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            String data_url = "http://192.168.43.146/androcrud/lihat.php";
            String json = "";
                try {

                    Log.d("ERORAJA", "INI DI DO IN BACKGROUND");
                    URL url = new URL(data_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);


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
                loadIntoRecycleview(result);

            }
        }

}
