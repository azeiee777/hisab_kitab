package com.example.hisabkitab.app_resource;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppMethods {
    private static final String TAG = "TAGG";

    public static Float rawFeetToRealFeet(String feet) {
        Log.d(TAG, "starting length " + feet);
        boolean checkDot = feet.contains(".");
        Log.d(TAG, "feetToInchh: " + checkDot);
        if(checkDot) {
            Log.d("TAGG", "feetToInchh: visited");
            String[] stringParts = feet.split("\\.");
            int feets = Integer.parseInt(stringParts[0]) * 12;
            Log.d(TAG, "converted " + feets);
            int inches = Integer.parseInt(stringParts[1]);
            Log.d(TAG, "converted " + inches);
            int finalInches = feets + inches;
            Log.d(TAG, "totall inch " + finalInches);
            float finalfeet = finalInches / 12f ;
            Log.d(TAG, "final feet " + finalfeet);
            return finalfeet;
        }else{
            Log.d("TAGG", "feetToInchh: visited else");
            float finalfeet = Float.parseFloat(feet);
            Log.d(TAG, "final feet " + finalfeet);
            return finalfeet;
        }
    }
}
