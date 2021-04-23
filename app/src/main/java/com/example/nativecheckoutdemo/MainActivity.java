package com.example.nativecheckoutdemo;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.nativecheckoutdemo.ordertoken.TokenQuickStartActivity;
import com.example.nativecheckoutdemo.paymentbutton.paymentButtonStartActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.config.CheckoutConfig;
import com.paypal.checkout.config.Environment;
import com.paypal.checkout.config.SettingsConfig;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.UserAction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Application application=this.getApplication();
        PayPalCheckout.setConfig(new CheckoutConfig(
                application,
                "AR-GGIH73fD1GDFwGllJ9mmPPGZkWLDNU8RTS8aefoZ8Pf51AEngAT4z-NtJMr3U0EwinncrK-uZNJ5H",
                Environment.SANDBOX,
                "com.example.nativecheckoutdemo://paypalpay",
                CurrencyCode.USD,
                UserAction.PAY_NOW,
                new SettingsConfig(true, false)));


        Button buyWithOrder=(Button)findViewById(R.id.buyWithOrder);
        Button buyWithOrderToken=(Button)findViewById(R.id.buyWithOrderToken);
        Button buyWithPaymentButton=(Button)findViewById(R.id.buyWithPaymentButton);
        buyWithPaymentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentbtnintent = new Intent( application , paymentButtonStartActivity.class);
                startActivity(paymentbtnintent);

            }

        });

        buyWithOrderToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent paymentbtnintent = new Intent( application , TokenQuickStartActivity.class);
                startActivity(paymentbtnintent);

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}