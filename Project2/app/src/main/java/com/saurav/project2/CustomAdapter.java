package com.saurav.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<String> carNames;
    private ArrayList<Integer> carImages;
    private Context mContext;

    public CustomAdapter(ArrayList<String> names, ArrayList<Integer> images, Context context)
    {
        mContext = context;
        carNames = names;
        carImages = images;
    }

    @Override
    public int getCount() {
        return carNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.row_data,parent,false);
        //getting view in row_data
        TextView name = convertView.findViewById(R.id.texts);
        ImageView image = convertView.findViewById(R.id.images);
        //set the text and image
        name.setText(carNames.get(position));
        image.setImageResource(carImages.get(position));
        return convertView;
    }
}
