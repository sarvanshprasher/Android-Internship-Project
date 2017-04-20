package com.example.shivam.catchup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Mangeaccount extends AppCompatActivity {
    Spinner spinner;
    String[] menuarray = {"Edit Profile", "Change Password", "Feedback", "Suggest to Friends", "Reset Data"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mangeaccount);






        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listaccount, menuarray);

        ListView listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(Mangeaccount.this, Editprofileactivity.class);
                    startActivity(intent);

                }

                if (position == 1) {
                    Intent intent = new Intent(Mangeaccount.this, Changepassword.class);
                    startActivity(intent);
                }
                if (position == 2) {
                    Intent i = new Intent(Mangeaccount.this, Feedback.class);
                    startActivity(i);
                }
            }
        });


    }
}


