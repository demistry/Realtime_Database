package com.android.firebasesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class FireBaseLogin extends AppCompatActivity {
    private FirebaseAuth auth;
    private static final int REQUEST_CODE= 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_login);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, FireBaseSignUpPage.class));
            finish();
        } else {
            authenticateUser();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IdpResponse response = IdpResponse.fromResultIntent(data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == ResultCodes.OK) {
                startActivity(new Intent(this, FireBaseSignUpPage.class));
                return;
            }
        } else {
            if (response == null) {// User cancelled Sign-in
                return;
            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
// Device has no network connection
                return;
            }
            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
// Unknown error occurred
                return;
            }
        }
    }

    private void authenticateUser(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
        .setAvailableProviders(getProviderList())
        .setIsSmartLockEnabled(false)
        .build(), REQUEST_CODE);
    }
    private List<AuthUI.IdpConfig> getProviderList(){
        List<AuthUI.IdpConfig> providers = new ArrayList<>();
        providers.add(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build());
        return providers;
    }
}
