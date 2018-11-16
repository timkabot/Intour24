package com.innopolis.intour24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.innopolis.intour24.App;
import com.innopolis.intour24.R;
import com.innopolis.intour24.model.entity.SightProperty;

import java.util.ArrayList;

/**
 * Created by Timkabor on 6/8/2017.
 */

public class IconsGridViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<SightProperty> properties;

    public IconsGridViewAdapter(Context context, ArrayList<SightProperty> properties) {
        mContext = context;
        this.properties = properties;
    }

    @Override
    public int getCount() {
        return properties.size();
    }

    @Override
    public Object getItem(int i) {
        return properties.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final SightProperty property = properties.get(i);
        if(view == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            view = layoutInflater.inflate(R.layout.card_icon, null);
        }

        final ImageView propertyIcon  = (ImageView)view.findViewById(R.id.invalidSign);;
        final TextView propertyDescription = (TextView)view.findViewById(R.id.accessibleForInvalids);

        App.setDrawableByName(property.getIcon(),propertyIcon,mContext);

        propertyDescription.setText(property.getName());
        return view;
    }
}
