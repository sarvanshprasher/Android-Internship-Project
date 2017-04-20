package com.example.shivam.catchup;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ToggleButton;


public class alertFragment extends Fragment {
    ToggleButton tb1,tb2;
    ImageButton b1;




    public alertFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_alert, container, false);
        EditText edittext10= (EditText) v.findViewById(R.id.editText10);


        b1= (ImageButton) v.findViewById(R.id.imageButton3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateDialog  da=new DateDialog( v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                da.show(ft,"DATEpicker");

            }
        });






        return v;
    }




}
