package com.example.safetysphere;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] mainTitle;
    private final String[] subTitle;
    private final int[] imageArray;


    public MyListAdapter(Activity context, String[] mainTitle, String[] subTitle, int[] imageArray) {
        super(context,R.layout.custom_layout,mainTitle);
        this.context = context;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.imageArray = imageArray;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layout=context.getLayoutInflater();
        View rowView=layout.inflate(R.layout.custom_layout,null,true);
        TextView text=rowView.findViewById(R.id.textview1);
        TextView text1=rowView.findViewById(R.id.textview2);
        ImageView imageView=rowView.findViewById(R.id.imageview);
        text.setText(mainTitle[position]);
        text1.setText(subTitle[position]);
        imageView.setImageResource(imageArray[position]);
        return rowView;
    }
}
