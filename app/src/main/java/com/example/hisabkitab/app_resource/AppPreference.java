package com.example.hisabkitab.app_resource;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class AppPreference {
    private static final String LANGUAGE_NAME = "hisab_kitab_shared_prefrence";
    private static AppPreference sIntance;
    private SharedPreferences mPref;

    private AppPreference(Context context){
        mPref = context.getSharedPreferences(LANGUAGE_NAME, MODE_PRIVATE);
    }

    public static void init(Context context){
        if(sIntance == null){
            sIntance = new AppPreference(context.getApplicationContext());
        }
    }

    public static AppPreference getInstance(){
        if(sIntance!=null){
            return sIntance;
        }
        throw new IllegalArgumentException("initialise prefrence first using init()");
    }

    public void putString(String key, String val){
        mPref.edit().putString(key, val).apply();
    }

    public String getString(String key, String defaultValue){
        return mPref.getString(key,defaultValue);
    }
}
