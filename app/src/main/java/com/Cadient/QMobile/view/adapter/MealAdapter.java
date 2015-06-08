package com.Cadient.QMobile.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.model.Meal;
import com.Cadient.QMobile.model.RecipeNutrient;
import com.Cadient.QMobile.utils.Nutrient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexey Vereshchaga on 20.10.14.
 */
public class MealAdapter extends AbstractAdapter<Meal> {

    private Callback callback;
    private TextView secondItemRow;
    private ImageView imageDelete;

    public MealAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        imageDelete = (ImageView) convertView.findViewById(R.id.img_delete);
        Meal meal = getItem(position);
        ((TextView) convertView.findViewById(R.id.row1)).setText(meal.getName());
        Map<Integer, Integer> mapOfNutrients = new HashMap<Integer, Integer>();
        for (RecipeNutrient recipeNutrient : meal.getRecipeNutrients()) {
            mapOfNutrients.put(recipeNutrient.getNutrientId(), Math.round(recipeNutrient.getAmount() != null ? recipeNutrient.getAmount() : 0));
        }
        //2nd row
        // StringBuilder builderSecondRow = new StringBuilder();

//        secondItemRow = ((TextView) convertView.findViewById(R.id.row2));
//        builderSecondRow.append("Serves: " + meal.getServingSizeAmount());
//        builderSecondRow.append(meal.getPrepTimeInMinutes() == null ? "" : " | Prep Time: " + meal.getPrepTimeInMinutes());
//        secondItemRow.setText(builderSecondRow);
        //3rd row
        ((TextView) convertView.findViewById(R.id.row3)).setText("Calories: " + mapOfNutrients.get(Nutrient.CALORIE.getId())
                + " | Fat: " + mapOfNutrients.get(Nutrient.FAT.getId()) + "g"
                + " | Carbs: " + mapOfNutrients.get(Nutrient.CARBS.getId()) + "g"
                + " | Protein: " + mapOfNutrients.get(Nutrient.PROTEIN.getId()) + "g"
                + " | Sodium: " + mapOfNutrients.get(Nutrient.SODIUM.getId()) + "mg");

        setVisibilityOfViews();
        return convertView;
    }

    public void registerCallback(Callback callback) {
        this.callback = callback;
    }

    private void setVisibilityOfViews() {
//        secondItemRow.setVisibility(View.VISIBLE);
        imageDelete.setVisibility(View.GONE);
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
