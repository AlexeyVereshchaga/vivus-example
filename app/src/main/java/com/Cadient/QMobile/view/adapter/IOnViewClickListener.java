package com.Cadient.QMobile.view.adapter;

import android.view.View;

import com.Cadient.QMobile.model.remote.FoodLogEntries;

/**
 * Created by ivan on 10/27/14.
 */
public interface IOnViewClickListener {
    void onFavoriteCheckedChange(FoodLogEntries entries, View view, boolean state);

    void onDeleteFoodLogEntries(FoodLogEntriesAdapter adapter, FoodLogEntries entries, View view);

    void onEditFoodLogEntries(FoodLogEntriesAdapter adapter, FoodLogEntries entries);
}
