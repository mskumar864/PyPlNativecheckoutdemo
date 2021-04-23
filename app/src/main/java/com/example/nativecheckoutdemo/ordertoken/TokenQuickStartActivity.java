package com.example.nativecheckoutdemo.ordertoken;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.Header;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nativecheckoutdemo.R;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.paypal.checkout.PayPalCheckout;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.cancel.OnCancel;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.error.OnError;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class TokenQuickStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_quick_start);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      /*  FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        TextInputLayout var10000 = (TextInputLayout) this.findViewById(R.id.totalAmountInput);
        Intrinsics.checkNotNullExpressionValue(var10000, "totalAmountInput");


        Button ppButton = (Button) findViewById(R.id.submitTokenButton);
        final Activity mActivity = TokenQuickStartActivity.this;
        Log.i("Suresh******  before  ", "before onClick");
        Log.i("Suresh******  before  ", "before onClick");
        ppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.i("Suresh******  enter  ", "onClick");
                String url = "";

                AsyncHttpClient client = new AsyncHttpClient();
                client.setTimeout(7000);
                client.setConnectTimeout(7000);
                client.setResponseTimeout(7000);
                RequestParams params = new RequestParams();
     /*   params.setForceMultipartEntityContentType(true);
        params.setContentEncoding("application/x-www-form-urlencoded");*/
                //
                //params.put("firstName", ((TextView)findViewById(R.id.firstname)).getText().toString());
                // params.put("lastName", ((TextView)findViewById(R.id.lastnametext)).getText().toString());
                //    params.put("email", ((TextView)findViewById(R.id.emailtext)).getText().toString());
                // Log.i("Suresh******  params  ",params.toString());
//                Log.i("Suresh******  email  ",((TextView)findViewById(R.id.emailtext)).getText().toString());
                client.post("https://pcp-orderv2.herokuapp.com/orderv2/create", params,
                        new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                                String body = new String(responseBody);
                                Log.d(" CreateOrder Response  ", body);
                                String url = "";
                                // String url="https://www.sandbox.paypal.com/IN/merchantsignup/partner/onboardingentry?token=NWFlMWRlMTUtZTRhOC00M2E0LTkyMDMtMmFlZjk0OWY5ZTU2OEpXbUtZM3QyMzR2SHR6aWZhMDRlYU9SQ3dkRGtnWGFheXVFK3c2dzdFOD0=&context_token=710968388633626624";
                                try {
                                    JSONObject jsonObject = new JSONObject(body);
                                    Log.d("Suresh******Order data ", jsonObject.toString());
                                    JSONArray array = jsonObject.getJSONArray("links");
                                    url = array.getJSONObject(1).get("href").toString();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {
                                Log.e("checkout  ", error.getMessage());
                            }


                            // Your implementation here
                        }
                );
                Log.d("Suresh******  exit  ", "created order");


            }


        });


    }







}