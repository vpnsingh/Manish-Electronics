package com.shubham.abhi.manish_electronics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Order extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    private Spinner sp1,sp2,sp3,sp4,sp5;
    private Button bt1,bt2;
    private Bundle bundle;
    private EditText et1,et2,et3,et4;
    String vendorName = "", vendorLocation = "";
    String product = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp1 = (Spinner) findViewById(R.id.vendor);
        sp1.setOnItemSelectedListener(this);

        sp2 = (Spinner) findViewById(R.id.products);
        sp2.setOnItemSelectedListener(this);

        sp3 = (Spinner) findViewById(R.id.products1);
        sp3.setOnItemSelectedListener(this);

        sp4 = (Spinner) findViewById(R.id.products2);
        sp4.setOnItemSelectedListener(this);

        sp5 = (Spinner) findViewById(R.id.products3);
        sp5.setOnItemSelectedListener(this);

        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);

        bt1 = (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.vendors, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.product1, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.product2, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,
                R.array.product3, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this,
                R.array.product4, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(adapter1);
        sp2.setAdapter(adapter2);
        sp3.setAdapter(adapter3);
        sp4.setAdapter(adapter4);
        sp5.setAdapter(adapter5);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

        String s1=String.valueOf(sp1.getSelectedItem());
        String s2=String.valueOf(sp2.getSelectedItem());

    if(s1.contentEquals("Select Vendor")){

        Snackbar.make(v, (CharSequence) "Select Vendors", -1).setAction((CharSequence) "Action", null).show();
        sp1.setFocusable(true);

    }else {


        switch (v.getId()) {
            case R.id.bt1:
                if (product.equals("") || product.isEmpty()) {

                    Intent i = new Intent(getApplicationContext(), Confirmation_page.class);

                    /******* Create SharedPreferences *******/
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    /**************** Storing data as KEY/VALUE pair *******************/

                    editor.putString("vendors_sp1", sp1.getSelectedItem().toString()); // Saving string

                    editor.putString("products_sp2", sp2.getSelectedItem().toString()); // Saving string
                   // editor.putInt("quantity_product1", Integer.parseInt(et1.getText().toString()));
                    editor.putString("quantity_product1",et1.getText().toString());

                    editor.putString("products_sp3", sp3.getSelectedItem().toString()); // Saving string
                    //editor.putInt("quantity_product2", Integer.parseInt(et2.getText().toString()));
                    editor.putString("quantity_product2", et2.getText().toString());

                    editor.putString("products_sp4", sp4.getSelectedItem().toString()); // Saving string
                   // editor.putInt("quantity_product3", Integer.parseInt(et3.getText().toString()));
                    editor.putString("quantity_product3", et3.getText().toString());

                    editor.putString("products_sp5", sp5.getSelectedItem().toString()); // Saving string
                    //editor.putInt("quantity_product4", Integer.parseInt(et4.getText().toString()));
                    editor.putString("quantity_product4", et4.getText().toString());

                    // Save the changes in SharedPreferences
                    editor.commit(); // commit changes

                    startActivity(i);

                    finish();

                } else
                    Toast.makeText(this, "Your order list is empty!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt2:
                Intent i = new Intent(getApplicationContext(), Ordercancelled.class);
                startActivity(i);
                finish();
                break;
        }
    }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
