package com.Cadient.QMobile.model.remote;

import com.Cadient.QMobile.model.BaseModel;
import com.Cadient.QMobile.model.Food;

/**
 * Created by Alexey Vereshchaga on 01.09.14.
 */
public class FoodRemote extends BaseModel {

    private Food food;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
