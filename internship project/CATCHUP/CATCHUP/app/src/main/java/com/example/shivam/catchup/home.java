package com.example.shivam.catchup;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    ImageView im1,im2,im3,im4,im5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        im1= (ImageView) findViewById(R.id.imageView6);
        im2= (ImageView) findViewById(R.id.imageView7);
        im3= (ImageView) findViewById(R.id.imageView8);
        im4= (ImageView) findViewById(R.id.imageView9);
        im5= (ImageView) findViewById(R.id.imageView10);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public  void goadd(View view){
        Intent i=new Intent(home.this,addtripActivity.class);
        startActivity(i);
    }
    public void goimag(View view){
        Intent i=new Intent(home.this,addtripActivity.class);
        startActivity(i);

    }
    public void goview(View view){
        Intent i=new Intent(home.this,edittrip.class);
        startActivity(i);
    }
    public void goedit(View view){
        Intent i=new Intent(home.this,viewtripmain.class);
        startActivity(i);
    }
    public void gofuture(View view){
        Intent i=new Intent(home.this,futureevent.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else {
            if (id == R.id.nav_AddTrip) {


                Intent i = new Intent(home.this, addtripActivity.class);
                startActivity(i);
                finish();


            } else if (id == R.id.nav_EditTrip) {

                Intent i = new Intent(home.this, viewtripmain.class);
                startActivity(i);

            } else if (id == R.id.nav_FutureTrip) {
                Intent i = new Intent(home.this, futureevent.class);
                startActivity(i);

            } else if (id == R.id.nav_viewtrip) {

                Intent i = new Intent(home.this, edittrip.class);
                startActivity(i);

            } else if (id == R.id.nav_Backup) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage(R.string.decision);
                alertDialogBuilder.setPositiveButton(R.string.positive_button,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                alertDialogBuilder.setNegativeButton(R.string.negative_button,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            } else if (id == R.id.nav_account) {
                Intent i = new Intent(home.this, Mangeaccount.class);
                startActivity(i);
            } else if (id == R.id.nav_rate) {
                Intent i=new Intent(home.this,ratetheapp.class);
                startActivity(i);
            } else if (id == R.id.nav_logout) {
                Intent i=new Intent(home.this,Main2Activity.class);
                startActivity(i);

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}

