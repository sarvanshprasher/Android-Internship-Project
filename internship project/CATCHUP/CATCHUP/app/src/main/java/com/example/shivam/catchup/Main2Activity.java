package com.example.shivam.catchup;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity {

    boolean status;
    Button b1,b2;
    String userid;
    SharedPreferences preferences;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        if (id==android.R.id.home)
        {
    Toast.makeText(Main2Activity.this, "Back Button Clicked", Toast.LENGTH_SHORT).show();
       }
        return true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        preferences=getSharedPreferences("mypre", Context.MODE_PRIVATE);
        b1= (Button) findViewById(R.id.signintext);
        b2= (Button) findViewById(R.id.signup);

    }


    public void gopage(View view)
    {
        Intent i=new Intent(Main2Activity.this,signup.class);
        startActivity(i);

    }
    public  void gohome(View v)
    {

        ProgressDialog progressDialog =new ProgressDialog(Main2Activity.this);
        progressDialog.setTitle("Signing");
        progressDialog.setMessage("Signing.....please wait");
        progressDialog.show();

        EditText utext= (EditText) findViewById(R.id.username);


        EditText ptext= (EditText) findViewById(R.id.password);
        final String user=utext.getText().toString();
        String pass=ptext.getText().toString();
        NetClass netClass= new NetClass();
        netClass.execute(user,pass);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(status==true)
                {
                    Intent i=new Intent(Main2Activity.this,home.class);
                    i.putExtra("mytext",user);


                    startActivity(i);
                }
                else
                {
                    Intent i =new Intent(Main2Activity.this,Main2Activity.class);
                    startActivity(i);
                }
            }
        },400);


    }





    public class NetClass extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String uname=params[0];
            String upass=params[1];

            String myUrlData="user_id="+uname+"&password="+upass;
            Log.d("My Url Data is ",myUrlData);
            StringBuffer buffer= new StringBuffer();
            try {
                URL myurl= new URL("http://10.0.3.2/select_data.php");
                HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
                connection.setRequestMethod("POST");
                connection.getOutputStream().write(myUrlData.getBytes());

                int responce=connection.getResponseCode();
                Log.d("Responce code is ",""+responce);
                if (responce==HttpURLConnection.HTTP_OK)
                {
                    InputStreamReader isr= new InputStreamReader(connection.getInputStream());
                    BufferedReader reader= new BufferedReader(isr);
                    String line;

                    while((line=reader.readLine())!=null)
                    {
                        buffer.append(line);
                    }

                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Data is ",s);
            try {
                JSONObject jsonObject= new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("result");

                JSONObject object=jsonArray.getJSONObject(0);

                userid=object.getString("id");
                preferences.edit().putString("id",userid).commit();



            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(userid!=null)
            {
                status=true;
            }
            else
            {
                status=false;
            }


        }
    }
}

