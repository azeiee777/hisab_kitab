package com.example.hisabkitab.products;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hisabkitab.R;
import com.example.hisabkitab.app_resource.AppMethods;

import org.w3c.dom.Text;

public class BaluFragment extends Fragment {

    private EditText length, width, rate, height, percentage;
    private TextView tvLength, tvWidth, tvTotalFeet, tvTotalMoney, tvTotalFeetResult, tvTotalMoneyResult, tvPlyRate, tvHight, tvPercentage;
    private Button btnsubmit, btnReset;
    private Animation animation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_balu, container, false);
        View view = inflater.inflate(R.layout.fragment_balu, container, false);
        length = (EditText) view.findViewById(R.id.et_ply_length);
        width = (EditText) view.findViewById(R.id.et_ply_width);
        height = (EditText) view.findViewById(R.id.et_ply_height);
        percentage = (EditText) view.findViewById(R.id.et_ply_percentage);

        rate = (EditText) view.findViewById(R.id.et_ply_rate);

        tvLength = (TextView) view.findViewById(R.id.tv_ply_length);
        tvWidth = (TextView) view.findViewById(R.id.tv_ply_width);
        tvHight = (TextView) view.findViewById(R.id.tv_ply_height);
        tvPlyRate = (TextView) view.findViewById(R.id.tv_ply_rate);
        tvPercentage = (TextView) view.findViewById(R.id.tv_ply_percentage);
        tvTotalFeet = (TextView) view.findViewById(R.id.tv_total_feet_text);
        tvTotalMoney = (TextView) view.findViewById(R.id.tv_total_money_text);
        tvTotalFeetResult = (TextView) view.findViewById(R.id.tv_total_feet_result);
        tvTotalMoneyResult = (TextView) view.findViewById(R.id.tv_total_money_result);

        btnsubmit = (Button) view.findViewById(R.id.btn_ply_submit);
        btnReset = (Button) view.findViewById(R.id.btn_ply_reset);
        updateViewsForLanguage();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                float fl, fw, r, fh, p;
                //Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                String strLength = length.getText().toString();
                String strWidth = width.getText().toString();
                String strHeight = height.getText().toString();
                String strRate = rate.getText().toString();
                String strPercentage = percentage.getText().toString();

                if (strLength == null || strLength == "" || strLength == " ") {
                    fl =0;
                } else {
                    try {
                        fl = AppMethods.rawFeetToRealFeet(strLength);
                    } catch (Exception e) {
                        fl =0;
                    }
                }
                if (strWidth == null || strWidth == "" || strWidth == " ") {
                    fw = 0;
                } else {
                    try {
                        fw = AppMethods.rawFeetToRealFeet(strWidth);
                    } catch (Exception e) {
                        fw = 0;
                    }
                }
                if (strRate == null || strRate == "" || strRate == " ") {
                    r = 0;
                } else {
                    try {
                        r = Float.parseFloat(strRate);
                    } catch (Exception e) {
                        r = 0;
                    }
                }
                if (strHeight == null || strHeight == "" || strHeight == " ") {
                    fh = 0;
                } else {
                    try {
                        fh = AppMethods.rawFeetToRealFeet(strHeight);
                    } catch (Exception e) {
                        fh = 0;
                    }
                }
                if (strPercentage == null || strPercentage == "" || strPercentage == " ") {
                    p = 0;
                } else {
                    try {
                        p = Float.parseFloat(strPercentage);
                    } catch (Exception e) {
                        p = 0;
                    }
                }
                visibleViews();
                double resultFeet = fl * fh * fw *1f ;
                double percentageShrink = resultFeet * p / 100f;
                double totalFeet = (resultFeet + percentageShrink);
                tvTotalFeetResult.setText(String.format("%.02f", totalFeet));
                tvTotalMoneyResult.setText(String.format("%.02f", totalFeet * r));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                clearViews();
            }
        });
        length.requestFocus();
        return view;
    }

    private void clearViews() {
        length.setText("");
        width.setText("");
        rate.setText("");
        percentage.setText("");
        height.setText("");
        tvTotalFeetResult.setText("");
        tvTotalMoneyResult.setText("");
        length.requestFocus();
        invisbleViews();
    }

    private void updateViewsForLanguage() {
        if (ProductActivity.getLanguage().equals("hindi")) {
            tvLength.setText(R.string.length_hindi);
            tvWidth.setText(R.string.width_hindi);
            tvHight.setText(R.string.height_hindi);
            tvPlyRate.setText(R.string.rate_hindi);
            tvTotalFeet.setText(R.string.total_feet_hindi);
            tvTotalMoney.setText(R.string.total_money_hindi);
            tvPercentage.setText(R.string.percentage_hindi);
            btnsubmit.setText(R.string.button_submit_hindi);
            btnReset.setText(R.string.button_reset_hindi);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.balu_hindi);

        } else {
            tvLength.setText(R.string.length_english);
            tvWidth.setText(R.string.width_english);
            tvHight.setText(R.string.height_english);
            tvPlyRate.setText(R.string.rate_english);
            tvTotalFeet.setText(R.string.total_feet_english);
            tvTotalMoney.setText(R.string.total_money_english);
            tvPercentage.setText(R.string.percentage_english);
            btnsubmit.setText(R.string.button_submit_english);
            btnReset.setText(R.string.button_reset_english);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.balu_english);

        }
        invisbleViews();
    }

    private void invisbleViews() {
        tvTotalFeet.setVisibility(View.INVISIBLE);
        tvTotalMoney.setVisibility(View.INVISIBLE);
        tvTotalFeetResult.setVisibility(View.INVISIBLE);
        tvTotalMoneyResult.setVisibility(View.INVISIBLE);
    }

    private void visibleViews() {
        tvTotalFeet.setVisibility(View.VISIBLE);
        tvTotalMoney.setVisibility(View.VISIBLE);
        tvTotalFeetResult.setVisibility(View.VISIBLE);
        tvTotalMoneyResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        animation = AnimationUtils.loadAnimation(context.getApplicationContext(), R.anim.blink);
    }
}