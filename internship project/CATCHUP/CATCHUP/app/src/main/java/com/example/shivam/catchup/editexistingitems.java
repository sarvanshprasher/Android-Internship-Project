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
import java.util.ArrayList;
import java.util.HashMap;

public class editexistingitems extends AppCompatActivity {

    EditText tc,tc1,tc2,tc3,tc4,tc5,tc6,tc7;
    String trip_id;

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

    ListView list;
    SharedPreferences preferences;
    boolean status;
    String user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editexistingitems);
        peoples=new JSONArray();
        tc= (EditText) findViewById(R.id.editText20);
        tc1= (EditText) findViewById(R.id.editText22);
        tc2= (EditText) findViewById(R.id.editText23);
        tc3= (EditText) findViewById(R.id.editText24);
        tc4= (EditText) findViewById(R.id.editText25);
        tc5= (EditText) findViewById(R.id.editText26);
        tc6= (EditText) findViewById(R.id.editText27);
        tc7= (EditText) findViewById(R.id.editText28);

        Intent intent=getIntent();
        String trip_id=intent.getStringExtra("trip_id");






        tripList = new ArrayList<HashMap<String,String>>();



        preferences=editexistingitems.this.getSharedPreferences("mypre", Context.MODE_PRIVATE);
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

                trip_id=c.getString("id");
                    String name = c.getString(TAG_TRIPNAME);
                    String startdate = c.getString(STARTDATE);
                    String returndate = c.getString(RETURNDATE);
                    String details = c.getString(DETAILS);
                    String expense = c.getString(EXPENSE);
                    String events = c.getString(EVENTS);
                    String liked = c.getString(LIKED);
                    String unliked = c.getString(UNLIKED);



                tc.setText(name);
                tc1.setText(startdate);
                tc2.setText(returndate);
                tc3.setText(details);
                tc4.setText(expense);
                tc5.setText(events);
                tc6.setText(liked);
                tc7.setText(unliked);



            } catch (JSONException e1) {
                e1.printStackTrace();
            }


                }
            }

    public void updateitem(View view)
    {

        String name=tc.getText().toString();
        String startdate=tc1.getText().toString();
        String returndate=tc2.getText().toString();
        String detail=tc3.getText().toString();
        String expense=tc4.getText().toString();
        String events=tc5.getText().toString();
        String liked=tc6.getText().toString();
        String unliked=tc7.getText().toString();




        UpdateItems updateItems= new UpdateItems();
        updateItems.execute(name,startdate,returndate,detail,expense,events,liked,unliked);

    }

    class UpdateItems extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... params) {
        String myUrlData="id="+trip_id+"&tripname="+params[0]+"&startdate="+params[1]+"&returndate="+params[2]+"&details="+params[3]+"&expense="+params[4]+"&events="+params[5]+"&liked="+params[6]+"&unliked="+params[7];
            StringBuffer buffer= new StringBuffer();
            try {
                URL myurl= new URL("http://10.0.3.2/edit_existing_trip.php");

            HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
            connection.setRequestMethod("POST");
                connection.getOutputStream().write(myUrlData.getBytes());
int response=connection.getResponseCode();
                Log.d("responce is ",""+response);
                if(response==HttpURLConnection.HTTP_OK)
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

            Log.d("Result is ",buffer.toString());
            return null;
        }
    }


        }









