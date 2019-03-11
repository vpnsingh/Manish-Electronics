package com.shubham.abhi.manish_electronics;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ordercancelled extends AppCompatActivity {
    private Button cancel;
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercancelled);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cancel=(Button) findViewById(R.id.cancel);
        et1=(EditText) findViewById(R.id.et1);        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.equals("")||et1.equals(null)){
                    Snackbar.make(v, (CharSequence) "Please Enter The Reason!!!", -1).setAction((CharSequence) "Action", null).show();
                }
                else {
                    Toast.makeText(Ordercancelled.this, "Order Cancelled", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
