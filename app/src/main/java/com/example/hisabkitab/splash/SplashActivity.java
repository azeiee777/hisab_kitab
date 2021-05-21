package com.example.hisabkitab.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hisabkitab.app_resource.SetColor;
import com.example.hisabkitab.home.HomeActivity;
import com.example.hisabkitab.R;
import com.example.hisabkitab.app_resource.AppPreference;
import com.example.hisabkitab.app_resource.AppPrefrenceKey;
import com.example.hisabkitab.language_selection.LanguageSelectionActivity;

public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        SetColor.setStatusBarColor(this,this,R.color.splash_background_color);
        AppPreference.init(this);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (AppPreference.getInstance().getString(AppPrefrenceKey.SELECTED_LANGUAGE, null) == null) {
                        Intent intent = new Intent(SplashActivity.this, LanguageSelectionActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        thread.start();
    }
}