package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.view.fragment.view.MenuItem;

/**
 * Created by ivan on 10/7/14.
 */
public class DrawerAdapter extends AbstractAdapter<MenuItem> {

    public DrawerAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        ImageView icon = (ImageView) convertView.findViewById(R.id.menu_item_icon);
        icon.setImageResource(getItem(position).getIcon());

        TextView name = (TextView) convertView.findViewById(R.id.menu_item_title);
        name.setText(getItem(position).getTitle());

        return convertView;
    }
}
