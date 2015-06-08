package com.Cadient.QMobile.model;

/**
 * Created by alexey on 20.08.14.
 */
public class Quantity extends BaseModel {
    private String unit;
    private float amount;


    public Quantity() {
    }

    public Quantity(String unit, float amount) {
        this.unit = unit;
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
