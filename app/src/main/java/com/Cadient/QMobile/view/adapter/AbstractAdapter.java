package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by ivan on 10/7/14.
 */
public abstract class AbstractAdapter<T> extends ArrayAdapter<T> {

    protected int resource;

    public AbstractAdapter(Context context, int resource) {
        super(context, resource);
        this.resource = resource;
    }
}
