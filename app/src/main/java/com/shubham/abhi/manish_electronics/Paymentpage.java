package com.shubham.abhi.manish_electronics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Paymentpage extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private TextView tv1, tv2, upper_total, total, discount;
    private Button payment;
    private ProgressBar progressBar;
    String region, location, products_sp2, products_sp3, products_sp4, products_sp5;
    String quantity_product1, quantity_product2, quantity_product3, quantity_product4;
    int total_p1, total_p2, total_p3, total_p4, total_amount;
    RadioGroup paymentOptions;
    double total_discount;
    String vendorName = "", vendorLocation = "";
    String vendors_sp1,formateddate,formateddate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        upper_total = (TextView) findViewById(R.id.upper_total);
        total = (TextView) findViewById(R.id.total);
        discount = (TextView) findViewById(R.id.discount);

        paymentOptions = (RadioGroup) findViewById(R.id.rg);

        payment = (Button) findViewById(R.id.pay);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        region = pref.getString("region_sp1", null); // getting String
        location = pref.getString("location_sp2", null); // getting String

        vendors_sp1 = pref.getString("vendors_sp1", null);

        products_sp2 = pref.getString("products_sp2", null);
        quantity_product1 = pref.getString("quantity_product1", null);
        total_p1 = Integer.parseInt(quantity_product1) * 2800;

        products_sp3 = pref.getString("products_sp3", null);
        quantity_product2 = pref.getString("quantity_product2", null);
        total_p2 = Integer.parseInt(quantity_product2) * 3500;

        products_sp4 = pref.getString("products_sp4", null);
        quantity_product3 = pref.getString("quantity_product3", null);
        total_p3 = Integer.parseInt(quantity_product3) * 4800;

        products_sp5 = pref.getString("products_sp5", null);
        quantity_product4 = pref.getString("quantity_product4", null);
        total_p4 = Integer.parseInt(quantity_product4) * 2500;

        total_amount = total_p1 + total_p2 + total_p3 + total_p4;

        total.setText("Total Amount : Rs " + total_amount);
        upper_total.setText("Rs " + total_amount);

        total_discount = total_amount * 0.1;

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.Payment));
        setSupportActionBar(toolbar);
        */

        paymentOptions.setOnCheckedChangeListener(this);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
        formateddate = df.format(c.getTime());
        formateddate1 = df1.format(c.getTime());
        tv1.setText("Date :" + formateddate);
        tv2.setText("Time :" + formateddate1);
        //progressBar.setMax(100);

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newOrderFileName = vendorName.replace("\\s+", "") + "-" + getCurrentDate().replaceAll("/", "-") + ".txt";
                OrderLocationService orderLocationService = new OrderLocationService(getBaseContext());
                Location myLocation = orderLocationService.getLocation();

                if (myLocation != null){

                    try

                        {

                            File sdCard = Environment.getExternalStorageDirectory();
                            // /mnt/media-rw/sdcard/

                            File directory = new File(sdCard.getAbsolutePath() +
                                    "/MyFiles");
                            directory.mkdirs();

                            File file = new File(directory, "textfile.txt");

                            FileOutputStream fOut = new FileOutputStream(file);
                            OutputStreamWriter orderWrite = new OutputStreamWriter(fOut);

                            //---write the string to the file---
                            orderWrite.append("Latitude: " + String.valueOf(myLocation.getLatitude())
                                    + " Longitude: " + String.valueOf(myLocation.getLongitude()) + "\n");

                            orderWrite.append("Date -> " + formateddate + "\n");
                            orderWrite.append("Date -> " + formateddate1 + "\n");
                            orderWrite.append("Region -> " + region + "\n");
                            orderWrite.append("Location -> " + location + "\n");
                            orderWrite.append("Vendors Name ->" + vendors_sp1 + "\n");

                            orderWrite.append("Product 1 -> " + products_sp2 + "\n");
                            orderWrite.append("Quantity -> " + quantity_product1 + "\n");
                            orderWrite.append("Cost -> Rs. " + total_p1 + "\n");

                            orderWrite.append("Product 2 -> " + products_sp3 + "\n");
                            orderWrite.append("Quantity -> " + quantity_product2 + "\n");
                            orderWrite.append("Cost -> Rs. " + total_p2 + "\n");

                            orderWrite.append("Product 3 -> " + products_sp4 + "\n");
                            orderWrite.append("Quantity -> " + quantity_product3 + "\n");
                            orderWrite.append("Cost -> Rs. " + total_p3 + "\n");

                            orderWrite.append("Product 4 -> " + products_sp5 + "\n");
                            orderWrite.append("Quantity -> " + quantity_product4 + "\n");
                            orderWrite.append("Cost -> Rs. " + total_p4 + "\n");

                            if (paymentOptions.getCheckedRadioButtonId() == R.id.rb_cash) {
                                orderWrite.append("Total Cost -> Rs. " + total_amount * 0.1 + "\n");
                            } else {
                                orderWrite.append("Total Cost -> Rs. " + total_amount + "\n");
                            }

                            orderWrite.flush();
                            orderWrite.close();

                            //---display file saved message---
                            Toast.makeText(getBaseContext(),
                                    "Ordered Placed successfully!", Toast.LENGTH_SHORT).show();


                            Intent i = new Intent(getApplicationContext(), Logout.class);
                            startActivity(i);

                        } catch(
                        IOException ioe)

                        {
                            ioe.printStackTrace();
                        }

                }else {
                    Toast.makeText(getApplicationContext(), "Order cannot be placed!", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

        switch (checkedId) {
            case R.id.rb_cash:

                upper_total.setText("Rs " + total_amount * 0.1);//10% discount
                total.setText("Total Amount : Rs " + total_discount);
                discount.setText("10 % Discount");
                total.setText("Total Amount : Rs " + total_discount);

                break;
            case R.id.rb_card:

                upper_total.setText("Rs " + total_amount);
                total.setText("Total Amount : Rs " + total_amount);
                discount.setText("No Discount on Credit Card");

                break;
        }

    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

}

