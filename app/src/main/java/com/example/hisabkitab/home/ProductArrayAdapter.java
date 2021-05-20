package com.example.hisabkitab.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hisabkitab.R;

public class ProductArrayAdapter extends ArrayAdapter<String> {
   Context context;
   String productName[];
   int productImage[];

    ProductArrayAdapter(Context context,String proName[], int proImg[]) {
        super(context, R.layout.row,R.id.imv_product,proName);
        this.context = context;
        this.productImage = proImg;
        this.productName = proName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row,parent,false);
       // return super.getView(position, convertView, parent);

        ImageView img = row.findViewById(R.id.imv_product);
        TextView tv = row.findViewById(R.id.tv_product);

        img.setImageResource(productImage[position]);
        tv.setText(productName[position]);
        return row;
    }
}
