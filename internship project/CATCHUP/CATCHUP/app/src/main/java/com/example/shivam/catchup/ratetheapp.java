package com.example.shivam.catchup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

public class ratetheapp extends AppCompatActivity {
    RatingBar rate;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratetheapp);
        rate = (RatingBar) findViewById(R.id.ratingBar);
        b1= (Button) findViewById(R.id.button3);
rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        Toast.makeText(ratetheapp.this,"you selected="+String.valueOf(rating),Toast.LENGTH_LONG).show();
    }
});
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating=rate.getRating();
                Toast.makeText(ratetheapp.this,"you selected="+String.valueOf(rating),Toast.LENGTH_LONG).show();
            }
        });


    }
}
