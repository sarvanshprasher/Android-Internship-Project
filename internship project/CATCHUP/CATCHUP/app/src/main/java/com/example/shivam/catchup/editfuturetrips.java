package com.example.shivam.catchup;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class editfuturetrips extends Fragment {

    String myJSON;
    List<String> trip_id;

    private static final String TAG_RESULTS="result";
    private static final String DESTINATIONPLACCE = "destinationplaces";
    JSONArray peoples ;

    ArrayList<HashMap<String, String>> tripList;

    ListView list;


    public editfuturetrips() {
        // Required empty public constructor
    }
    SharedPreferences preferences;
    boolean status;
    String user_id;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_editfuturetrips, container, false);

        peoples=new JSONArray();




        trip_id= new ArrayList<>();



        list = (ListView) root. findViewById(R.id.listView);
        tripList = new ArrayList<HashMap<String,String>>();



        preferences=getActivity().getSharedPreferences("mypre", Context.MODE_PRIVATE);
        String user_id=preferences.getString("id","");
        My_Tour_Info my_tour_info=new My_Tour_Info();
        my_tour_info.execute(user_id);
        return root;



    }

    class My_Tour_Info extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String user_id = params[0];
            String myUrlData = "user_id=" + user_id;
            StringBuffer buffer = new StringBuffer();

            try {
                URL url = new URL("http://10.0.3.2/futurebyuser.php");
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

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);

                    String name = c.getString(DESTINATIONPLACCE);

                    trip_id.add(c.getString("id"));

                    HashMap<String, String> persons = new HashMap<String, String>();

                    persons.put(DESTINATIONPLACCE, name);
                    tripList.add(persons);


                    ListAdapter adapter = new SimpleAdapter(
                            getContext(), tripList, R.layout.list_item,
                            new String[]{DESTINATIONPLACCE},
                            new int[]{R.id.id1}
                    );

                    list.setAdapter(adapter);

                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent = new Intent(getActivity(), editfutureitems.class);
                            intent.putExtra("trip_id",trip_id.get(position));
                            startActivity(intent);




                        }
                    });




                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }}


