package com.example.shivam.catchup;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
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


public class futureviewitems extends AppCompatActivity {

    String myJSON;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_DESTINATIONCOUNTRY = "destinationcountry";
    private static final String DESTINATIONSTATE = "destinationstate";
    private static final String DESTINATIONPLACE = "destinationplaces";
    private static final String TRIPWITH = "tripwith";

    private static final String TAG_STARTDATE = "startdate";
    private static final String RETURNDATE = "returndate";
    private static final String NOOFDAYS = "noofdays";

    private static final String TICKETS = "ticket";
    private static final String NOOFMEMEBERS = "noofmembers";
    ListView list;


    JSONArray peoples;



    SharedPreferences preferences;
    boolean status;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futureviewitems);

        Intent intent = getIntent();
        String trip_id = intent.getStringExtra("trip_id");

        peoples = new JSONArray();
        list = (ListView) findViewById(R.id.listView);


        preferences = futureviewitems.this.getSharedPreferences("mypre", Context.MODE_PRIVATE);
        String user_id = preferences.getString("id", "");
        My_Tour_Info my_tour_info = new My_Tour_Info();
        my_tour_info.execute(trip_id);
        My_Tour_Info1 my_tour_info1 = new My_Tour_Info1();
        my_tour_info1.execute(trip_id);
        My_Tour_Info2 my_tour_info2 = new My_Tour_Info2();
        my_tour_info2.execute(trip_id);

    }

    class My_Tour_Info extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String myUrlData = "trip_id=" + user_id;
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL("http://10.0.3.2/viewfuturetrip.php");
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

                String name = c.getString(TAG_DESTINATIONCOUNTRY);
                String startdate = c.getString(DESTINATIONSTATE);
                String returndate = c.getString(DESTINATIONPLACE);
                String details = c.getString(TRIPWITH);


                TextView tc = (TextView) findViewById(R.id.textView45);
                tc.setText(name);
                TextView tc1 = (TextView) findViewById(R.id.textView47);
                tc1.setText(startdate);
                TextView tc2 = (TextView) findViewById(R.id.textView49);
                tc2.setText(returndate);
                TextView tc3 = (TextView) findViewById(R.id.textView51);
                tc3.setText(details);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    class My_Tour_Info1 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String user_id = params[0];
            String myUrlData = "trip_id=" + user_id;
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL("http://10.0.3.2/view_future_trip_date.php");
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

                String name = c.getString(TAG_STARTDATE);
                String startdate = c.getString(RETURNDATE);
                String returndate = c.getString(NOOFDAYS);



                TextView tc = (TextView) findViewById(R.id.textView38);
                tc.setText(name);
                TextView tc1 = (TextView) findViewById(R.id.textView40);
                tc1.setText(startdate);
                TextView tc2 = (TextView) findViewById(R.id.textView42);
                tc2.setText(returndate);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }


    class My_Tour_Info2 extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {

            String user_id = params[0];
            String myUrlData = "trip_id=" + user_id;
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL("http://10.0.3.2/view_future_trip_ticket.php");
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

                String name = c.getString(TICKETS);
                String startdate = c.getString(NOOFMEMEBERS);



                TextView tc = (TextView) findViewById(R.id.textView54);
                tc.setText(name);
                TextView tc1 = (TextView) findViewById(R.id.textView56);
                tc1.setText(startdate);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}



