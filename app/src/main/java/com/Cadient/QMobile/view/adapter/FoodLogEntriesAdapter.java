package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.model.RecipeNutrient;
import com.Cadient.QMobile.model.remote.FoodLogEntries;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Nutrient;
import com.Cadient.QMobile.utils.Unit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 10/27/14.
 */
public class FoodLogEntriesAdapter extends AbstractAdapter<FoodLogEntries> {

    private IOnViewClickListener iOnViewClickListener;

    public FoodLogEntriesAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class ViewHolder {
        public CheckBox btnFavorite;
        public TextView nameMeal, countServings, calories;
        public ImageView btnDeleteFoodEntries;
        public LinearLayout layoutEdit;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        Map<Integer, String> mapOfNutrients = new HashMap<Integer, String>();
        for (RecipeNutrient recipeNutrient : getItem(position).getMeal().getRecipeNutrients()) {
            mapOfNutrients.put(recipeNutrient.getNutrient().getId(), Integer.toString(Math.round(recipeNutrient.getAmount())));
        }
        findViewById(holder, convertView);

        FoodLogEntries entry = getItem(position);
        holder.nameMeal.setText(entry.getMeal().getName());

        holder.countServings.setText("" + HelpUtils.calculateAmountOfUnit(entry.getMultiplier(), entry.getUnitId(), entry.getMeal().getUnitId(), entry.getMeal().getServingSizeAmount()) + " " + Unit.getNameById(entry.getUnitId())); //" Servings"
        holder.calories.setText("Calories: " + mapOfNutrients.get(Nutrient.CALORIE.getId()));

        Boolean isFavorite = false;
        if (getItem(position).getMeal() != null
                && getItem(position).getMeal().getFavoriteUserMeal() != null
                && getItem(position).getMeal().getFavoriteUserMeal().getId() != null) {
            isFavorite = getItem(position).getMeal().getFavoriteUserMeal().getId() > 0;
        }
        holder.btnFavorite.setChecked(isFavorite);
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnViewClickListener.onFavoriteCheckedChange(getItem(position), v, ((CheckBox) v).isChecked());
            }
        });

        holder.btnDeleteFoodEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnViewClickListener.onDeleteFoodLogEntries(FoodLogEntriesAdapter.this, getItem(position), v);
            }
        });

        holder.layoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnViewClickListener.onEditFoodLogEntries(FoodLogEntriesAdapter.this, getItem(position));
            }
        });

        return convertView;
    }

    public void setOnViewClickListener(IOnViewClickListener onViewClickListener) {
        this.iOnViewClickListener = onViewClickListener;
    }

    private void findViewById(ViewHolder holder, View convertView) {
        holder.nameMeal = (TextView) convertView.findViewById(R.id.name_meal);
        holder.countServings = (TextView) convertView.findViewById(R.id.count_servings);
        holder.calories = (TextView) convertView.findViewById(R.id.calories);
        holder.btnFavorite = (CheckBox) convertView.findViewById(R.id.btn_favorite);
        holder.btnDeleteFoodEntries = (ImageView) convertView.findViewById(R.id.btn_delete_food_entries);
        holder.layoutEdit = (LinearLayout) convertView.findViewById(R.id.ll_edit);
    }
}
