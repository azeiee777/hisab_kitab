package com.example.hisabkitab.language_selection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.hisabkitab.home.HomeActivity;
import com.example.hisabkitab.R;
import com.example.hisabkitab.app_resource.AppPreference;
import com.example.hisabkitab.app_resource.AppPrefrenceKey;

public class LanguageSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnEnglish, btnHindi;
    private Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);
        AppPreference.init(this);
        initLayout();
    }

    private void initLayout() {
        btnEnglish =(Button) findViewById(R.id.btn_eng_lang);
        btnHindi =(Button) findViewById(R.id.btn_hindi_lang);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        btnEnglish.setOnClickListener(this);
        btnHindi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_eng_lang :
                Toast.makeText(this, "english selected",Toast.LENGTH_SHORT).show();
                //AppPreference.selectedLanguage = "english";
                //appPreference.pref.edit().putString(AppConstants.selectedLanguage,"english").apply();
                v.startAnimation(animation);
                AppPreference.getInstance().putString(AppPrefrenceKey.SELECTED_LANGUAGE,"english");
                openHomeScreen();
                break;
            case R.id.btn_hindi_lang :
                Toast.makeText(this, "हिंदी चुना गया",Toast.LENGTH_SHORT).show();
                v.startAnimation(animation);
                //AppPreference.selectedLanguage = "hindi";
                //appPreference.pref.edit().putString(AppConstants.selectedLanguage,"hindi").apply();
                AppPreference.getInstance().putString(AppPrefrenceKey.SELECTED_LANGUAGE,"hindi");
                openHomeScreen();
                break;
        }
    }

    private void openHomeScreen(){
        Intent intent = new Intent(LanguageSelectionActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}