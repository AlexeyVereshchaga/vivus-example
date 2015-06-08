package com.Cadient.QMobile.utils;

/**
 * Created by Alexey Vereshchaga on 21.10.14.
 */
public enum Nutrient {
    CALORIE(4), FAT(21), CARBS(6), PROTEIN(5), SODIUM(64);

    private Integer id;

    Nutrient(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
