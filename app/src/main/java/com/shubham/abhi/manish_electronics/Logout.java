package com.shubham.abhi.manish_electronics;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Logout extends AppCompatActivity {

    ImageButton ib_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed by Vipin Singh & Shubham Singh", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ib_logout=(ImageButton) findViewById(R.id.ib_logout);
        ib_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(getApplicationContext(), "Successfully logout", Toast.LENGTH_SHORT).show();
                startActivity(i);

            }
        });
    }

}
