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

public class WoodFragment extends Fragment {

    private EditText length, width, rate, height, piece;
    private TextView tvLength, tvWidth, tvTotalFeet, tvTotalMoney, tvTotalFeetResult,
            tvTotalMoneyResult, tvPlyRate, tvHight, tvPiece;
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
        View view = inflater.inflate(R.layout.fragment_wood, container, false);
        length = (EditText) view.findViewById(R.id.et_ply_length);
        width = (EditText) view.findViewById(R.id.et_ply_width);
        height = (EditText) view.findViewById(R.id.et_ply_height);
        piece = (EditText) view.findViewById(R.id.et_ply_pc);

        rate = (EditText) view.findViewById(R.id.et_ply_rate);

        tvLength = (TextView) view.findViewById(R.id.tv_ply_length);
        tvWidth = (TextView) view.findViewById(R.id.tv_ply_width);
        tvHight = (TextView) view.findViewById(R.id.tv_ply_height);
        tvPlyRate = (TextView) view.findViewById(R.id.tv_ply_rate);
        tvPiece = (TextView) view.findViewById(R.id.tv_ply_pc);
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
                float fl, w, r, h, p;
                //Toast.makeText(getContext(),"clicked",Toast.LENGTH_SHORT).show();
                String strLength = length.getText().toString();
                String strWidth = width.getText().toString();
                String strHeight = height.getText().toString();
                String strRate = rate.getText().toString();
                String strPc = piece.getText().toString();

                if (strLength == null || strLength == "" || strLength == " ") {
                    fl = 0;
                } else {
                    try {
                        fl = AppMethods.rawFeetToRealFeet(strLength);
                    } catch (Exception e) {
                        fl = 0;
                    }
                }
                if (strWidth == null || strWidth == "" || strWidth == " ") {
                    w = 0;
                } else {
                    try {
                        w = Float.parseFloat(strWidth);
                    } catch (Exception e) {
                        w = 0;
                    }
                }
                if (strPc == null || strPc == "" || strPc == " ") {
                    p = 0;
                } else {
                    try {
                        p = Float.parseFloat(strPc);
                    } catch (Exception e) {
                        p = 0;
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
                    h = 0;
                } else {
                    try {
                        h = Float.parseFloat(strHeight);
                    } catch (Exception e) {
                        h = 0;
                    }
                }
                visibleViews();
                tvTotalFeetResult.setText(String.format("%.02f", fl * w * h * p / 144f));
                tvTotalMoneyResult.setText(String.format("%.02f", fl * h * w * r * p / 144f));
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearViews();
                v.startAnimation(animation);
            }
        });
        length.requestFocus();
        return view;
    }

    private void clearViews() {
        length.setText("");
        width.setText("");
        rate.setText("");
        height.setText("");
        piece.setText("");
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
            tvPiece.setText(R.string.piece_hindi);
            tvTotalFeet.setText(R.string.total_feet_hindi);
            tvTotalMoney.setText(R.string.total_money_hindi);
            btnsubmit.setText(R.string.button_submit_hindi);
            btnReset.setText(R.string.button_reset_hindi);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.wood_hindi);

        } else {
            tvLength.setText(R.string.length_english);
            tvWidth.setText(R.string.width_english);
            tvHight.setText(R.string.height_english);
            tvPlyRate.setText(R.string.rate_english);
            tvPiece.setText(R.string.piece_english);
            tvTotalFeet.setText(R.string.total_feet_english);
            tvTotalMoney.setText(R.string.total_money_english);
            btnsubmit.setText(R.string.button_submit_english);
            btnReset.setText(R.string.button_reset_english);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.wood_english);

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