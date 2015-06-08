package com.Cadient.QMobile.view.adapter;

import android.view.View;
import android.widget.ArrayAdapter;

import com.Cadient.QMobile.model.BaseModel;

/**
 * Created by Alexey Vereshchaga on 11.11.14.
 */
public interface IDeleteItemListener<T extends AbstractAdapter, E extends BaseModel> {

    void deleteItem(T adapter, E entries, View view);
}
