package com.example.shivam.catchup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 5000;
    protected static final int TIMER_RUNTIME=5000;
    protected boolean mbActive;
    protected ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById((R.id.progressBar));
        final Thread timerThread=new Thread(){


            public void run()
            {
                mbActive=true;
                try{

                    int waited=0;
                    while(mbActive && (waited < TIMER_RUNTIME))
                    {

                        sleep(200);
                        if(mbActive)
                        {
                            waited+=200;
                            updateprogress(waited);


                        }

                    }
                }

                catch (InterruptedException e)
                {


                }finally {
                    onContinue();
                }
            }
        };timerThread.start();





        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    public void updateprogress(final int timepassed)
    {
        if(null != progressBar)
        {
            final int progress = progressBar.getMax()*timepassed/TIMER_RUNTIME;
            progressBar.setProgress(progress);
        }
    }

    public void onContinue()
    {
        Log.d("final message","signing in");
    }


}