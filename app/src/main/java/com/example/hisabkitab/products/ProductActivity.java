package com.example.hisabkitab.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;

import com.example.hisabkitab.R;
import com.example.hisabkitab.app_resource.AppConstants;
import com.example.hisabkitab.app_resource.AppPreference;
import com.example.hisabkitab.app_resource.AppPrefrenceKey;

public class ProductActivity extends AppCompatActivity {

    private String proValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        AppPreference.init(this);
        Intent intent = getIntent();
        proValue = intent.getStringExtra(AppConstants.SEND_DATA);
        openFragment(proValue);
    }

    private void openFragment(String proValue) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (proValue.equals("ply") || proValue.equals("प्लाई")) {
            PlyFragment fragment = new PlyFragment();
            fragmentManager.beginTransaction().replace(R.id.containerr, fragment).commit();
        } else if (proValue.equals("door") || proValue.equals("डोर")) {
            DoorFragment fragment = new DoorFragment();
            fragmentManager.beginTransaction().replace(R.id.containerr, fragment).commit();
        } else if (proValue.equals("wood") || proValue.equals("वुड")) {
            WoodFragment fragment = new WoodFragment();
            fragmentManager.beginTransaction().replace(R.id.containerr, fragment).commit();
        } else if (proValue.equals("balu") || proValue.equals("बालू")) {
            BaluFragment fragment = new BaluFragment();
            fragmentManager.beginTransaction().replace(R.id.containerr, fragment).commit();
        }
    }

    public static String getLanguage() {
        return AppPreference.getInstance().getString(AppPrefrenceKey.SELECTED_LANGUAGE, null);
    }
}