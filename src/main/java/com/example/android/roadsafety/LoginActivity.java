package com.example.android.roadsafety;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;


import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;



import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity  {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int RC_SIGN_IN = 101;


    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivityForResult(

                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                    .setLogo(R.mipmap.logo)
                    .setIsSmartLockEnabled(false)
                    .setProviders(AuthUI.EMAIL_PROVIDER)
                    .build(this),
                    RC_SIGN_IN);

        }
    }

    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode == RESULT_OK) {

                Intent intent = new Intent(this, MainActivity.class);
                finish();
                startActivity(intent);
            } else {

            }
        }
    }



}