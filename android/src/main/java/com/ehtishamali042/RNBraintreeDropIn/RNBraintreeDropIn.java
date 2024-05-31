package com.ehtishamali042.RNBraintreeDropIn;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseActivityEventListener;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.util.Map;
import java.util.HashMap;

public class RNBraintreeDropIn extends ReactContextBaseJavaModule {
    private static final int DROP_IN_REQUEST = 786;
    private Promise mPickerPromise;
    private static final String E_ACTIVITY_DOES_NOT_EXIST = "E_ACTIVITY_DOES_NOT_EXIST";
    private static final String E_NO_DATA_FOUND = "E_NO_DATA_FOUND";

    private final ActivityEventListener mActivityEventListener = new BaseActivityEventListener() {

        @Override
        public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent intent) {
            if (requestCode != DROP_IN_REQUEST || mPickerPromise == null) {
                return;
            }
            if (resultCode == Activity.RESULT_OK) {
                
                String error = intent.getStringExtra("error");
                if (error != null) {
                    mPickerPromise.reject("Error", "DropIn Failed");
                    return;
                } else {
                    sendPaymentMethodNonceResult(intent);
                }
            }
        }
    };
    private void sendPaymentMethodNonceResult(Intent intent) {
        String nonce = intent.getStringExtra("nonce");
        String type = intent.getStringExtra("type");
        String description = intent.getStringExtra("description");
        Boolean isDefault = Boolean.valueOf(intent.getStringExtra("isDefault"));

        if (mPickerPromise != null) {
            WritableMap result = Arguments.createMap();
            result.putString("type", type);
            result.putString("description", description);
            result.putBoolean("isDefault", isDefault);
            result.putString("nonce", nonce);
            mPickerPromise.resolve(result);
        }
    }
    RNBraintreeDropIn(ReactApplicationContext context) {
        super(context);
        context.addActivityEventListener(mActivityEventListener);
    }

    @Override
    public String getName() {
        return "RNBraintreeDropIn";
    }

    @ReactMethod
    public void showDropIn(final ReadableMap options, final Promise promise) {
        if (!options.hasKey("token")) {
            promise.reject("NO_CLIENT_TOKEN", "You must provide a client token");
            return;
        }

        Activity currentActivity = getCurrentActivity();
        if (currentActivity == null) {
            promise.reject(E_ACTIVITY_DOES_NOT_EXIST, "Activity doesn't exist");
            return;
        }

        // Store the promise to resolve/reject when picker returns data
        mPickerPromise = promise;


        Intent intent = new Intent(currentActivity, RNBraintreeDropInActivity.class);
        intent.putExtra("token", options.getString("token"));
        currentActivity.startActivityForResult(intent, DROP_IN_REQUEST);
        currentActivity.overridePendingTransition(0, 0);
    }
}