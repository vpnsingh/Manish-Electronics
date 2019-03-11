package com.shubham.abhi.manish_electronics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class
Location_activity extends AppCompatActivity {
    private Spinner sp1,sp2;
    private Button next;
    private Bundle bundle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp1 = (Spinner) findViewById(R.id.region);
        sp2 = (Spinner) findViewById(R.id.location);
        next=(Button) findViewById(R.id.bt1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        //Default Spinner View sp2

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Selected, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=String.valueOf(sp1.getSelectedItem());
                String s2=String.valueOf(sp2.getSelectedItem());

                if(s1.contentEquals("Select Region...")){

                    Snackbar.make(v, (CharSequence) "Select Region", -1).setAction((CharSequence) "Action", null).show();

                }
                else if(s2.contentEquals("Select Location...")){

                    Snackbar.make(v, (CharSequence) "Select Location", -1).setAction((CharSequence) "Action", null).show();

                }

                else{

                    Intent i=new Intent(getApplicationContext(),Order.class);

                    /******* Create SharedPreferences *******/
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    /**************** Storing data as KEY/VALUE pair *******************/

                    editor.putString("region_sp1",sp1.getSelectedItem().toString()); // Saving string
                    editor.putString("location_sp2",sp2.getSelectedItem().toString()); // Saving string

                    // Save the changes in SharedPreferences
                    editor.commit(); // commit changes

                    startActivity(i);
                }

            }
        });

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s1=String.valueOf(sp1.getSelectedItem());


                if(s1.contentEquals("Central")){

                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.Central, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(adapter1);

                }

                if(s1.contentEquals("Western")){

                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.Western, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(adapter1);


                }

                if(s1.contentEquals("Harbour")){

                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.Harbour, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp2.setAdapter(adapter1);

                }

                if(s1.contentEquals("Navi Mumbai")){

                    ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getBaseContext(),
                            R.array.NaviMumbai, android.R.layout.simple_spinner_item);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    sp2.setAdapter(adapter1);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }
}
