package com.example.shivam.catchup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class signup extends AppCompatActivity {


    SharedPreferences sharedpreferences;
    boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }


    public void getdata2(View view)
    {
        ProgressDialog progressDialog =new ProgressDialog(signup.this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Processing.....please wait");
        progressDialog.show();



        EditText fn= (EditText) findViewById(R.id.firstname);
        EditText ln= (EditText) findViewById(R.id.lastname);
        EditText un= (EditText) findViewById(R.id.username1);
        EditText mn= (EditText) findViewById(R.id.mobile);
        EditText email= (EditText) findViewById(R.id.email);
        EditText pass= (EditText) findViewById(R.id.password1);
        EditText gender= (EditText) findViewById(R.id.editText11);
        EditText userid= (EditText) findViewById(R.id.editText21);
        NetClass netClass=new NetClass();
        netClass.execute(fn.getText().toString(),ln.getText().toString(),un.getText().toString(),mn.getText().toString(),email.getText().toString(),pass.getText().toString(),gender.getText().toString(),userid.getText().toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(status==true)
                {
                    startActivity(new Intent(signup.this,Main2Activity.class));
                }
                else
                {
                    startActivity(new Intent(signup.this,signup.class));

                }
            }
        },400);

    }

    public class NetClass extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String firstn=params[0];
            String lastn=params[1];
            String usern=params[2];
            String mobile=params[3];
            String email=params[4];
            String pass=params[5];
            String gender=params[6];
            String userid=params[7];

            String myUrlData="firstname="+firstn+"&lastname="+lastn+"&username="+usern+"&mobile="+mobile+"&email="+email+"&password="+pass+"&gender="+gender+"&user_id="+userid;
            Log.d("My Url Data is ",myUrlData);
            StringBuffer buffer= new StringBuffer();
            try {
                URL myurl= new URL("http://movingnut.coolpage.biz/signupp.php");
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
            if(s.equals("success"))
            {
                status=true;
            }
            else
            {
                status=false;
            }
            Toast.makeText(signup.this,"sucessful",Toast.LENGTH_LONG).show();

        }
    }
}

