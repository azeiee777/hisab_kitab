package com.example.hisabkitab.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.hisabkitab.R;
import com.example.hisabkitab.app_resource.AppConstants;
import com.example.hisabkitab.app_resource.AppData;
import com.example.hisabkitab.app_resource.AppPreference;
import com.example.hisabkitab.app_resource.AppPrefrenceKey;
import com.example.hisabkitab.language_selection.LanguageSelectionActivity;
import com.example.hisabkitab.products.ProductActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HomeActivity extends AppCompatActivity {

    private AdView mAdView;
    private ListView lv;
    public static final String TAG = "HomeActivity";
    private Animation animation;
    private Button btnLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        AppPreference.init(this);
        initLayout();

        if (AppPreference.getInstance().getString(AppPrefrenceKey.SELECTED_LANGUAGE, null)
                .equals("hindi")) {
            ProductArrayAdapter adapter = new ProductArrayAdapter
                    (this, AppData.products_hindi,AppData.product_image);
            lv.setAdapter(adapter);
        } else {
            ProductArrayAdapter adapter = new ProductArrayAdapter
                    (this,AppData.products_english,AppData.product_image);
            lv.setAdapter(adapter);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = lv.getItemAtPosition(position).toString();
               /* Toast.makeText(HomeActivity.this,"clicked item is "
                        + str,Toast.LENGTH_SHORT ).show();*/
                Log.d(TAG, "onItemClick: ");
                view.startAnimation(animation);
                Intent intent = new Intent(HomeActivity.this, ProductActivity.class);
                intent.putExtra(AppConstants.SEND_DATA,str);
                startActivity(intent);
            }
        });

        btnLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                Intent intent = new Intent(HomeActivity.this, LanguageSelectionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initLayout() {
        lv = findViewById(R.id.listView);
        btnLanguage = (Button) findViewById(R.id.btnLanguage);
        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        if(AppPreference.getInstance().getString(AppPrefrenceKey.SELECTED_LANGUAGE, null)
                .equals("hindi")){
            btnLanguage.setText(R.string.button_language_hindi);
            getSupportActionBar().setTitle(R.string.app_name_hindi);

        }else{
            btnLanguage.setText(R.string.button_language_english);
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }
}