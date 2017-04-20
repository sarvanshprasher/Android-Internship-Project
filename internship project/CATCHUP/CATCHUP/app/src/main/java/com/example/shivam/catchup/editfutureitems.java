package com.example.shivam.catchup;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class editfutureitems extends AppCompatActivity {

    EditText tc,tc1,tc2,tc3,tc4,tc5,tc6,tc7,tc8;

    String trip_id;
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
        setContentView(R.layout.activity_editfutureitems);


        tc = (EditText) findViewById(R.id.editText34);
        tc1 = (EditText) findViewById(R.id.editText35);
        tc2 = (EditText) findViewById(R.id.editText36);
        tc3 = (EditText) findViewById(R.id.editText37);
        tc4 = (EditText) findViewById(R.id.editText29);
        tc5 = (EditText) findViewById(R.id.editText30);
        tc6 = (EditText) findViewById(R.id.editText31);
        tc7 = (EditText) findViewById(R.id.editText32);
        tc8 = (EditText) findViewById(R.id.editText33);


        Intent intent = getIntent();
        trip_id = intent.getStringExtra("trip_id");

        peoples = new JSONArray();
        list = (ListView) findViewById(R.id.listView);


        preferences = getSharedPreferences("mypre",Context.MODE_PRIVATE);
         user_id = preferences.getString("id", "1");
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



                tc.setText(name);
                tc1.setText(startdate);
                tc2.setText(returndate);
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




                tc4.setText(name);
                tc5.setText(startdate);
                tc6.setText(returndate);



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




                tc7.setText(name);
                tc8.setText(startdate);



            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public void updateitem(View view)
    {

        String startdate=tc.getText().toString();
        String returndate=tc1.getText().toString();
        String noofdays=tc2.getText().toString();
        String destinationcountry=tc3.getText().toString();
        String destinationstate=tc4.getText().toString();
        String destinationplace=tc5.getText().toString();
        String tripwith=tc6.getText().toString();
        String ticket=tc7.getText().toString();
        String noofmembers=tc8.getText().toString();





        UpdateItems updateItems= new UpdateItems();
        updateItems.execute(startdate,returndate,noofdays,destinationcountry,destinationstate,destinationplace,tripwith,ticket,noofmembers);

    }

    class UpdateItems extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... params) {
            String myUrlData="id="+user_id+"&startdate="+params[0]+"&returndate="+params[1]+"&noofdays="+params[2]+"&destinationcountry="+params[3]+"&destinationstate="+params[4]+"&destinationplace="+params[5]+"&tripwith="+params[6]+"&ticket="+params[7]+"&noofmembers="+params[8];
Log.d("Trip User id ",user_id);
            StringBuffer buffer= new StringBuffer();

            try {
                URL myurl= new URL("http://10.0.3.2/edit_trip_info.php");

                HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
                connection.setRequestMethod("POST");
                connection.getOutputStream().write(myUrlData.getBytes());
                int res=connection.getResponseCode();
                Log.d("Response updation is",""+res);
                if (res==HttpURLConnection.HTTP_OK)
                {
                    BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));

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

            Log.d("Result of updation ",buffer.toString());
            return null;
        }
    }
}
