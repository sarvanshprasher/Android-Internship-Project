package com.example.shivam.catchup;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
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
public class tripinfoFragment extends Fragment {
    ArrayList<String> selection = new ArrayList<String>();
    boolean status;
    Button b1;
    TextView tv;
    CheckBox ch1, ch2;


    public tripinfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.fragment_tripinfo, container, false);
        b1 = (Button) V.findViewById(R.id.button);

        ch1= (CheckBox) V.findViewById(R.id.checkBox1);
        ch2= (CheckBox) V.findViewById(R.id.checkBox2);
        ch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();

                if (checked) {
                    selection.add("Friends");
                } else {
                    selection.remove("Friends");
                }

            }
        });
        ch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();



                if (checked) {
                    selection.add("Family");
                } else {
                    selection.remove("Family");
                }
            }
        });




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String final_result = "";
                for (String selections : selection) {
                    final_result = final_result + selections + "\n";
                    tv.setText(final_result);
                    tv.setEnabled(true);

                    StringBuffer mydata = new StringBuffer();
                    mydata.append(tv);
                }



                ch1 = (CheckBox) V.findViewById(R.id.checkBox1);
                ch2 = (CheckBox) V.findViewById(R.id.checkBox2);
                EditText dc= (EditText) V.findViewById(R.id.editText12);
                EditText ds= (EditText) V.findViewById(R.id.editText13);
                EditText dp= (EditText) V.findViewById(R.id.editText14);
                NetClass netClass=new NetClass();
                netClass.execute(dc.getText().toString(),ds.getText().toString(),dp.getText().toString(),final_result.toString());

            }
        });


        return V;
    }




        public class NetClass extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                String descountry = params[0];
                String desstate = params[1];
                String deplaces = params[2];
                String tripwith = params[3];


                String myUrlData = "destinationcountry=" + descountry + "&destinationstate=" + desstate + "&destinationplaces=" + deplaces + "&tripwith=" + tripwith;
                Log.d("My Url Data is ", myUrlData);
                StringBuffer buffer = new StringBuffer();
                try {
                    URL myurl = new URL("http://10.0.3.2/tripinfofuture.php");
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
