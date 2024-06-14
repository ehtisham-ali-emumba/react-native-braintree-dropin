package com.ehtishamali042.RNBraintreeDropIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.braintreepayments.api.CardNonce;
import com.braintreepayments.api.DropInClient;
import com.braintreepayments.api.DropInListener;
import com.braintreepayments.api.DropInRequest;
import com.braintreepayments.api.DropInResult;
import com.braintreepayments.api.PaymentMethodNonce;

public class RNBraintreeDropInActivity extends AppCompatActivity implements DropInListener {
    DropInClient dropInClient;
    DropInRequest dropInRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.ehtishamali042.RNBraintreeDropIn.R.layout.activity_braintree_drop_in);

        Intent myIntent = getIntent();
        String tokenKey = myIntent.getStringExtra("token");
        if(dropInClient==null) {
            dropInClient = new DropInClient(this, tokenKey);
            dropInClient.setListener(this);
        }
    }
    @Override
    protected void onStart() {
        overridePendingTransition(0,0);
        super.onStart();

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(dropInRequest==null) {
            dropInRequest = new DropInRequest();
            dropInClient.launchDropIn(dropInRequest);
        }
    }

    @Override
    public void onDropInSuccess(@NonNull DropInResult dropInResult) {
        PaymentMethodNonce nonce = dropInResult.getPaymentMethodNonce();
        Intent output = new Intent();
        output.putExtra("nonce", nonce.getString());
        output.putExtra("isDefault", nonce.isDefault());
        output.putExtra("description", dropInResult.getPaymentDescription());
        output.putExtra("type", dropInResult.getPaymentMethodType().name());


        setResult(RESULT_OK, output);
        finish();
        this.overridePendingTransition(0, 0);
    }

    @Override
    public void onDropInFailure(@NonNull Exception error) {
        Intent output = new Intent();
        output.putExtra("error", "DropIn Failed");
        setResult(RESULT_OK, output);
        finish();
        this.overridePendingTransition(0, 0);
    }
}