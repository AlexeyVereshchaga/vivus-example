package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.model.Activity;

/**
 * Created by Alexey Vereshchaga on 12.11.14.
 */
public class SearchExerciseAdapter extends AbstractAdapter<Activity> {
    private Callback callback;


    public SearchExerciseAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.row1)).setText(getItem(position).getName());
        return convertView;
    }

    public void registerCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (callback != null) {
            callback.notifyDataSetChanged();
        }
    }

    public interface Callback {
        public void notifyDataSetChanged();
    }
}
