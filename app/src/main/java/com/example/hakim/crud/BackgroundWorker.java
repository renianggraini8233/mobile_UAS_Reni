package com.example.hakim.crud;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BackgroundWorker extends AsyncTask<String, Void, String> {
//    ListView listView;

    ProgressDialog progDailog;
    private Context context;

    public BackgroundWorker(Context context) {
//        listView = (ListView) listView.findViewById(R.id.list_item1);
        this.context = context;
        progDailog = new ProgressDialog(context);
    }


    protected void onPreExecute(){
        Log.d("ERORAJA", "INI DI DO IN PRE");
        super.onPreExecute();
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();

    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://192.168.43.146/androcrud/login.php";
        String register_url = "http://192.168.43.146/ e/regist.php";
        String update_url = "http://192.168.43.146/androcrud/update.php";
        String hapus_url = "http://192.168.43.146/androcrud/delete.php";
        if (type.equals("login")){
            try {
                String user = params[1];
                String pass = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"
                        +URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line="";
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("register")){
            try {
                String nama = params[1];
                String alamat = params[2];
                String tgl = params[3];
                String jk = params[4];
                String agama = params[5];

                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("nama", "UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"
                        +URLEncoder.encode("alamat", "UTF-8")+"="+URLEncoder.encode(alamat, "UTF-8")+"&"
                        +URLEncoder.encode("tgllahir", "UTF-8")+"="+URLEncoder.encode(tgl, "UTF-8")+"&"
                        +URLEncoder.encode("jenis_kelamin", "UTF-8")+"="+URLEncoder.encode(jk, "UTF-8")+"&"
                        +URLEncoder.encode("agama", "UTF-8")+"="+URLEncoder.encode(agama, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line="";
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("edit")){
            try {
                Log.d("Proseswow", "Ini di do in edit");
                String nama = params[1];
                String alamat = params[2];
                String tgl = params[3];
                String jk = params[4];
                String agama = params[5];
                String id = params[6];

                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("nama", "UTF-8")+"="+URLEncoder.encode(nama,"UTF-8")+"&"
                        +URLEncoder.encode("alamat", "UTF-8")+"="+URLEncoder.encode(alamat, "UTF-8")+"&"
                        +URLEncoder.encode("tgllahir", "UTF-8")+"="+URLEncoder.encode(tgl, "UTF-8")+"&"
                        +URLEncoder.encode("jenis_kelamin", "UTF-8")+"="+URLEncoder.encode(jk, "UTF-8")+"&"
                        +URLEncoder.encode("agama", "UTF-8")+"="+URLEncoder.encode(agama, "UTF-8")+"&"
                        +URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line="";
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(type.equals("hapus")){
            try {
                String id = params[1];

                URL url = new URL(hapus_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("id", "UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String result = "";
                String line="";
                while ((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d("Proseswow", "Ini di PostEXECTU");
        if (result.equalsIgnoreCase("true")) {
            progDailog.dismiss();
            Toast.makeText(context,"Password dan Username Benar", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(context, HomeActivity.class);
            context.startActivity(login);
            ((Activity)context).finish();
        }else if(result.equalsIgnoreCase("false")){
            progDailog.dismiss();
            Toast.makeText(context,"Password atau Username Salah", Toast.LENGTH_SHORT).show();
        }else if (result.equalsIgnoreCase("berhasilregist")){
            progDailog.dismiss();
            Toast.makeText(context,"Insert Berhasil", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(context, HomeActivity.class);
            context.startActivity(login);
            ((Activity)context).finish();
        }else if (result.equalsIgnoreCase("gagalregist")){
            progDailog.dismiss();
            Toast.makeText(context,"Insert Gagal", Toast.LENGTH_SHORT).show();
        }else if(result.equalsIgnoreCase("updateberhasil")){
            Log.d("Proseswow", "Ini postexecut in edit");
            progDailog.dismiss();
            Toast.makeText(context,"Update Berhasil", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, HomeActivity.class);
            context.startActivity(i);
            ((Activity)context).finish();
        }else if(result.equalsIgnoreCase("updategagal")){
            progDailog.dismiss();
            Toast.makeText(context,"Update Gagal", Toast.LENGTH_SHORT).show();
        }else if(result.equalsIgnoreCase("hapusberhasil")){
            progDailog.dismiss();
            Toast.makeText(context,"Hapus Berhasil", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, HomeActivity.class);
            context.startActivity(i);
        }else if(result.equalsIgnoreCase("hapusgagal")){
            progDailog.dismiss();
            Toast.makeText(context,"Hapus Gagal", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }
}
