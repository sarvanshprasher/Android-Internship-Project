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
import android.widget.ImageButton;
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
public class tripdatefragment extends Fragment {
    int year_x,month_x,day_x;
    static final  int DILOG_ID=0;
    boolean status;
    ImageButton im1,im2;
    Button b1;
    EditText et1,et2,et3;


    public tripdatefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View V= inflater.inflate(R.layout.fragment_tripdatefragment, container, false);
        b1= (Button) V.findViewById(R.id.button2);
        im1= (ImageButton) V.findViewById(R.id.imageButton);
        im2= (ImageButton) V.findViewById(R.id.imageButton2);
        EditText et1= (EditText) V.findViewById(R.id.editText6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et1= (EditText) V.findViewById(R.id.editText6);
                EditText et2= (EditText) V.findViewById(R.id.editText7);
                EditText et3= (EditText) V.findViewById(R.id.editText8);
                NetClass netClass=new NetClass();
                netClass.execute(et1.getText().toString(),et2.getText().toString(),et3.getText().toString());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(status==true)
                        {
                            Toast.makeText(getActivity(),"Succesfully added",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Try Later",Toast.LENGTH_LONG).show();
                        }
                    }
                },400);

            }




        });








        return V;




    }







    public class NetClass extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... params) {
            String startd=params[0];
            String returnd=params[1];
            String noofday=params[2];

            String myUrlData="startdate="+startd+"&returndate="+returnd+"&noofdays="+noofday;
            Log.d("My Url Data is ",myUrlData);
            StringBuffer buffer= new StringBuffer();
            try {
                URL myurl= new URL("http://10.0.3.2/tripdate.php");
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


        }
    }






}
