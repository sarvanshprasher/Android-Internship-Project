package com.example.shivam.catchup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class viewtripsitems extends AppCompatActivity {



    String myJSON;

    private static final String TAG_RESULTS="result";
    private static final String TAG_TRIPNAME = "tripname";
    private static final String STARTDATE = "startdate";
    private static final String RETURNDATE ="returndate";
    private static final String DETAILS ="details";
    private static final String EXPENSE ="expense";
    private static final String EVENTS ="events";
    private static final String LIKED ="liked";
    private static final String UNLIKED ="unliked";


    JSONArray peoples ;

    ArrayList<HashMap<String, String>> tripList;


    SharedPreferences preferences;
    boolean status;
    String user_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtripsitems);

        Intent intent=getIntent();
        String trip_id=intent.getStringExtra("trip_id");
        peoples=new JSONArray();
        tripList = new ArrayList<HashMap<String,String>>();

        preferences=viewtripsitems.this.getSharedPreferences("mypre", Context.MODE_PRIVATE);
        String user_id=preferences.getString("id","");
        My_Tour_Info my_tour_info=new My_Tour_Info();
        my_tour_info.execute(trip_id);


    }
    class My_Tour_Info extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String myUrlData = "trip_id=" + user_id;
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL("http://10.0.3.2/viewexistingtrip.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                connection.getOutputStream().write(myUrlData.getBytes());

                int response = connection.getResponseCode();
                Log.d("Response code is ", "" + response);
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    String line;

                    while ((line = reader.readLine()) != null) {
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
            Log.d("Data is ", s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                    JSONObject c = jsonArray.getJSONObject(0);

                    String name = c.getString(TAG_TRIPNAME);
                    String startdate = c.getString(STARTDATE);
                    String returndate = c.getString(RETURNDATE);
                    String details = c.getString(DETAILS);
                    String expense = c.getString(EXPENSE);
                    String events = c.getString(EVENTS);
                    String liked = c.getString(LIKED);
                    String unliked = c.getString(UNLIKED);
                TextView tc= (TextView) findViewById(R.id.editText20);
                tc.setText(name);
                TextView tc1= (TextView) findViewById(R.id.editText22);
                tc1.setText(startdate);
                TextView tc2= (TextView) findViewById(R.id.editText23);
                tc2.setText(returndate);
                TextView tc3= (TextView) findViewById(R.id.editText24);
                tc3.setText(details);
                TextView tc4= (TextView) findViewById(R.id.editText25);
                tc4.setText(expense);
                TextView tc5= (TextView) findViewById(R.id.editText26);
                tc5.setText(events);
                TextView tc6= (TextView) findViewById(R.id.editText27);
                tc6.setText(liked);
                TextView tc7= (TextView) findViewById(R.id.editText28);
                tc7.setText(unliked);












            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }}


