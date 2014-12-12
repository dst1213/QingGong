package com.example.qinggong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.adsmogo.splash.AdsMogoSplash;
import com.adsmogo.splash.AdsMogoSplashListener;
import com.adsmogo.util.AdsMogoSplashMode;
import com.example.qinggong.R;

/**
 * Created by ZJGJK03 on 2014/12/12.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        AdsMogoSplash adsMogoSplash = new AdsMogoSplash(SplashActivity.this, getResources().getString(R.string.MogoID), AdsMogoSplashMode.FULLSCREEN);
        adsMogoSplash.setAdsMogoSplashListener(new AdsMogoSplashListener() {
            @Override
            public void onSplashClickAd(String s) {

            }

            @Override
            public void onSplashRealClickAd(String s) {

            }

            @Override
            public void onSplashError(String s) {
                Intent intent=new Intent(SplashActivity.this,StartActivity.class);
                startActivity(intent);
            }

            @Override
            public void onSplashSucceed() {

            }

            @Override
            public void onSplashClose() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Back key disabled
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
