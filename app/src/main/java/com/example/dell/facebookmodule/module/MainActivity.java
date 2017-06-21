package com.example.dell.facebookmodule.module;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.dell.facebookmodule.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Collections;


public class MainActivity extends AppCompatActivity {
CallbackManager callbackManager;
 LoginButton loginButton;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout= (RelativeLayout) findViewById(R.id.relative);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Boolean Check_log_in = isLoggedIn();
        if(new CheckConnection(MainActivity.this).checkInternetConenction()){
            if (Check_log_in) {
                Intent intent = new Intent(MainActivity.this, Facebook_page.class);
                startActivity(intent);
                finish();
            } else
                LogIn();
        }
        else
        {
            Snackbar.make(relativeLayout,"No Internet Connection",Snackbar.LENGTH_LONG).show();
        }

    }

    void LogIn()
    {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        loginButton= (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Collections.singletonList("pages_show_list"));

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent intent=new Intent(MainActivity.this,Facebook_page.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                   finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("inside","OnActivityCreated");
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }
}
