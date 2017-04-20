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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ticketsFragment extends Fragment {
    ArrayList<String> selection=new ArrayList<String>();
    Button b1;
    CheckBox ch1,ch2,ch3;
    boolean status;


    public ticketsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.fragment_tickets, container, false);

        ch1 = (CheckBox) V.findViewById(R.id.checkBox6);
        ch2 = (CheckBox) V.findViewById(R.id.checkBox7);
        ch3 = (CheckBox) V.findViewById(R.id.checkBox8);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                if (checked) {
                    selection.add("Air Tickets");
                } else {
                    selection.remove("Air Tickets");
                }

            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean checked = ((CheckBox) v).isChecked();

                if (checked) {
                    selection.add("Bus Tickets");
                } else {
                    selection.remove("Bus Tickets");
                }


            }
        });
        ch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                if (checked) {
                    selection.add("Railway Tickets");
                } else {
                    selection.remove("Railway Tickets");
                }

            }
        });


        b1 = (Button) V.findViewById(R.id.button5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String final_result = "";
                for (String selections : selection) {
                    final_result = final_result + selections + "\n";
                }


                EditText et1 = (EditText) V.findViewById(R.id.editText9);

                NetClass netClass = new NetClass();
                netClass.execute(final_result.toString(), et1.getText().toString());


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (status) {
                            Toast.makeText(getActivity(), "data added successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getActivity(), "unsucessfull,try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                }, 400);






    }
        });

        return V;


}

        public class NetClass extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String tickets = params[0];
                String noofmembers = params[1];


                String myUrlData = "ticket=" + tickets + "&noofmembers=" + noofmembers;
                Log.d("My Url Data is ", myUrlData);
                StringBuffer buffer = new StringBuffer();
                try {
                    URL myurl = new URL("http://10.0.3.2/ticket.php");
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
                Toast.makeText(getActivity(), "success", Toast.LENGTH_LONG).show();

            }


        }


    }