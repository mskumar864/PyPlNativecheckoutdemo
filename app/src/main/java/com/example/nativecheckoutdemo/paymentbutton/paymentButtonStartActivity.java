package com.example.nativecheckoutdemo.paymentbutton;

import android.app.Application;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

import android.util.Log;
import android.view.View;

import com.example.nativecheckoutdemo.R;
import com.paypal.checkout.approve.Approval;
import com.paypal.checkout.approve.OnApprove;
import com.paypal.checkout.cancel.OnCancel;
import com.paypal.checkout.createorder.CreateOrder;
import com.paypal.checkout.createorder.CreateOrderAction;
import com.paypal.checkout.createorder.CreateOrderActions;
import com.paypal.checkout.createorder.CurrencyCode;
import com.paypal.checkout.createorder.OrderIntent;
import com.paypal.checkout.createorder.ShippingPreference;
import com.paypal.checkout.createorder.UserAction;
import com.paypal.checkout.error.ErrorInfo;
import com.paypal.checkout.error.OnError;
import com.paypal.checkout.order.Amount;
import com.paypal.checkout.order.AppContext;
import com.paypal.checkout.order.CaptureOrderResult;
import com.paypal.checkout.order.Order;
import com.paypal.checkout.order.PurchaseUnit;
import com.paypal.checkout.paymentbutton.PayPalButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class paymentButtonStartActivity extends AppCompatActivity {
 String tag="suresh";
    PayPalButton  paymentButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_button_start);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

          paymentButton=(PayPalButton)findViewById(R.id.paymentButton);
        Application application=this.getApplication();
        setupPaymentButton(application);

    }

    private final void setupPaymentButton( Application application) {
/*

       List<PurchaseUnit> purchaseUnitRequests = new ArrayList<PurchaseUnit>();
        Amount amount= new Amount(CurrencyCode.USD,"10.00",null);

        PurchaseUnit purchaseUnit=new PurchaseUnit(null,null,null,null,null,null,null,amount);
        purchaseUnitRequests.add(purchaseUnit);
        Order order = (new Order.Builder()).appContext(new AppContext((String)null, (String)null, (String)null, (String)null, (String)null, (ShippingPreference)null, UserAction.PAY_NOW, 63, (DefaultConstructorMarker)null)).intent(OrderIntent.CAPTURE).purchaseUnitList(CollectionsKt.listOf((new com.paypal.checkout.order.PurchaseUnit.Builder()).amount((new com.paypal.checkout.order.Amount.Builder()).value("0.01").currencyCode(CurrencyCode.USD).build()).build())).build();

*/

/*

        paymentButton.setup(new CreateOrder() {
            @Override
            public void create(@NotNull CreateOrderActions createOrderActions) {
             createOrderActions.create(order,(CreateOrderActions.OnOrderCreated)null);

            },

            OnApprove { approval ->
                    approval.orderActions.capture { captureOrderResult ->
                    Log.i("CaptureOrder", "CaptureOrderResult: $captureOrderResult")
            }
            }
        });

        )

*/


            paymentButton.setup(CreateOrder.Companion.invoke((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((CreateOrderActions)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull CreateOrderActions createOrderActions) {
                    Intrinsics.checkNotNullParameter(createOrderActions, "createOrderActions");
                    Log.v(paymentButtonStartActivity.this.tag, "CreateOrder");
                    Order order = (new Order.Builder()).
                            appContext(new AppContext((String)null,
                                    (String)null, (String)null,
                                    (String)null, (String)null, (ShippingPreference)null, UserAction.PAY_NOW)).
                            intent(OrderIntent.CAPTURE).
                            purchaseUnitList(CollectionsKt.listOf((new com.paypal.checkout.order.PurchaseUnit.Builder()).
                                    amount((new com.paypal.checkout.order.Amount.Builder()).value("0.01").
                                            currencyCode(CurrencyCode.USD).build()).build())).build();
                    boolean var3 = false;
                    boolean var4 = false;

                   // Log.d(paymentButtonStartActivity.this.tag, "Order: " + var2);
                    Unit var8 = Unit.INSTANCE;

                    createOrderActions.create(order, new CreateOrderActions.OnOrderCreated() {
                        @Override
                        public void onCreated(@NotNull String s) {

                        }
                    });

                }
            })), OnApprove.Companion.invoke((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((Approval)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull Approval approval) {
                    Intrinsics.checkNotNullParameter(approval, "approval");
                    Log.v(paymentButtonStartActivity.this.tag, "OnApprove");
                    Log.d(paymentButtonStartActivity.this.tag, "Approval details: " + approval);
                    approval.getOrderActions().capture((Function1)(new Function1() {
                        // $FF: synthetic method
                        // $FF: bridge method
                        public Object invoke(Object var1) {
                            this.invoke((CaptureOrderResult)var1);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(@NotNull CaptureOrderResult captureOrderResult) {
                            Intrinsics.checkNotNullParameter(captureOrderResult, "captureOrderResult");
                            Log.v(paymentButtonStartActivity.this.tag, "Capture Order");
                            Log.d(paymentButtonStartActivity.this.tag, "Capture order result: " + captureOrderResult);
                        }
                    }));
                }
            })), OnCancel.Companion.invoke((Function0)(new Function0() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke() {
                    this.invoke();
                    Log.v(paymentButtonStartActivity.this.tag, "OnCancel");
                    Log.d(paymentButtonStartActivity.this.tag, "Buyer cancelled the checkout experience.");
                    return Unit.INSTANCE;
                }


            })), OnError.Companion.invoke((Function1)(new Function1() {
                // $FF: synthetic method
                // $FF: bridge method
                public Object invoke(Object var1) {
                    this.invoke((ErrorInfo)var1);
                    return Unit.INSTANCE;
                }

                public final void invoke(@NotNull ErrorInfo errorInfo) {
                    Intrinsics.checkNotNullParameter(errorInfo, "errorInfo");
                    Log.v(paymentButtonStartActivity.this.tag, "OnError");
                    Log.d(paymentButtonStartActivity.this.tag, "Error details: " + errorInfo);
                }
            })));

    }
}