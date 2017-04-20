package com.example.shivam.catchup;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Changepassword extends AppCompatActivity {
    boolean status;
    Button b1;
SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        preferences=getSharedPreferences("mypre", Context.MODE_PRIVATE);

        b1= (Button) findViewById(R.id.button4);


    }

    public void submit(View view){
        EditText et1= (EditText) findViewById(R.id.editText15);
        EditText et2= (EditText) findViewById(R.id.editText16);
        EditText et3= (EditText) findViewById(R.id.editText17);
        EditText et4= (EditText) findViewById(R.id.editText18);

        if (et3.getText().toString().equals(et4.getText().toString()))
        {
            NetClass netClass=new NetClass();
            netClass.execute(et1.getText().toString(),et2.getText().toString());

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    if(status==true)
                    {
                        Toast.makeText(Changepassword.this,"sucessfuly changed",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(Changepassword.this,"Try Later",Toast.LENGTH_LONG).show();

                    }
                }
            },400);

        }
        else
        {
            Toast.makeText(Changepassword.this, "Both password mismatch", Toast.LENGTH_SHORT).show();
        }




    }
    public class NetClass extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String usern=params[0];
            String cpass=params[1];


            String user_id=preferences.getString("id","");
            String myUrlData="username="+usern+"&password="+cpass+"&user_id="+user_id;
            Log.d("My Url Data is ",myUrlData);
            StringBuffer buffer= new StringBuffer();
            try {
                URL myurl= new URL("http://10.0.3.2/changepassword.php");
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
            Toast.makeText(Changepassword.this,"success",Toast.LENGTH_LONG).show();

        }
    }
}