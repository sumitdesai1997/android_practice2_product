package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    Spinner product;
    TextView price, totalPrice;
    EditText quantity;
    Button buy;

    String products[] = {"Apple","Banana","Mangoes","Oranges"};
    double prices[] = {10.5,1.5,15,3};
    int stocks[] = {5,10,20,10};
    public static double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product = findViewById(R.id.prName);
        price = findViewById(R.id.priceTV);
        totalPrice = findViewById(R.id.totPriceTV);
        quantity = findViewById(R.id.quantityN);
        buy = findViewById(R.id.buyButton);

        // FOR SPINNER
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, products);
        product.setAdapter(adapter);
        product.setOnItemSelectedListener(this);

        // FOR BUTTON
        buy.setOnClickListener(this);
    }

    // FOR SPINNER
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        price.setText(String.valueOf(prices[position]));
    }

    //FOR SPINNER
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        price.setText(String.valueOf(prices[0]));
    }

    // FOR BUTTON
    @Override
    public void onClick(View v) {
        int position = product.getSelectedItemPosition();
        if (quantity.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), "Please enter the quantity", Toast.LENGTH_LONG).show();
        } else if(Integer.parseInt(quantity.getText().toString()) > stocks[position]){
            Toast.makeText(getBaseContext(), "Maximum available quantity is "+stocks[position], Toast.LENGTH_LONG).show();
        }else {
            int qty = Integer.parseInt(quantity.getText().toString());
            double prc = Double.parseDouble(price.getText().toString());
            stocks[position] -= qty;
            total += (qty * prc);
            totalPrice.setText(String.format("%.2f", total));
        }
    }
}