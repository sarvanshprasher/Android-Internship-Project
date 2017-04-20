package com.example.shivam.catchup;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class tripinformationFragment extends Fragment {
    Button b1;
    boolean status;


    public tripinformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V= inflater.inflate(R.layout.fragment_tripinformation, container, false);
        b1= (Button) V.findViewById(R.id.buttonsubmit1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                EditText et1= (EditText) V.findViewById(R.id.tripname);
                EditText et2= (EditText) V.findViewById(R.id.date1);
                EditText et3= (EditText) V.findViewById(R.id.date2);
                EditText et4= (EditText) V.findViewById(R.id.details);
                EditText et5= (EditText) V.findViewById(R.id.expense);
                EditText et6= (EditText)V.findViewById(R.id.events);
                EditText et7= (EditText) V.findViewById(R.id.plceslikes);
                EditText et8= (EditText) V.findViewById(R.id.plces3);
                EditText et9= (EditText) V.findViewById(R.id.editText19);
                NetClass netClass=new NetClass();
                String t1=et1.getText().toString();
                String t2=et2.getText().toString();
                String t3=et3.getText().toString();
                String t4=et4.getText().toString();
                String t5=et5.getText().toString();
                String t6=et6.getText().toString();
                String t7=et7.getText().toString();
                String t8=et8.getText().toString();
                String t9=et9.getText().toString();


                netClass.execute(t1,t2,t3,t4,t5,t6,t7,t8,t9);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(status)
                        {
Toast.makeText(getActivity(),"data added successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
Toast.makeText(getActivity(),"unsucessfull,try again later",Toast.LENGTH_LONG).show();
                        }
                    }
                },400);}
        });











        return V;





    }


            public class NetClass extends AsyncTask<String, Void, String> {

                @Override
                protected String doInBackground(String... params) {
                    String tripn = params[0];
                    String startdate = params[1];
                    String returndate = params[2];
                    String details = params[3];
                    String expense = params[4];
                    String events = params[5];
                    String liked = params[6];
                    String unliked = params[7];
                    String user_id=params[8];

                    String myUrlData = "tripname=" + tripn + "&startdate=" + startdate + "&returndate=" + returndate + "&details=" + details + "&expense=" + expense + "&events=" + events + "&liked=" + liked + "&unliked=" + unliked+"&user_id="+user_id;
                    Log.d("My Url Data is ", myUrlData);
                    StringBuffer buffer = new StringBuffer();
                    try {
                        URL myurl = new URL("http://10.0.3.2/tripinfo.php");
                        HttpURLConnection connection = (HttpURLConnection) myurl.openConnection();
                        connection.setRequestMethod("POST");
                        connection.getOutputStream().write(myUrlData.getBytes());

                        int responce = connection.getResponseCode();
                        Log.d("Responce code is ", "" + responce);
                        if (responce == HttpURLConnection.HTTP_OK) {
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
                    if (s.equals("success")) {
                        status = true;
                    } else {
                        status = false;
                    }
                    Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();

                }
            }



}