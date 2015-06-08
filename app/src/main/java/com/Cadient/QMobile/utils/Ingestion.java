package com.Cadient.QMobile.utils;

/**
 * Created by Alex on 01.11.2014.
 */
public enum Ingestion {
    BREAKFAST(1), SNACK1(2), LUNCH(3), SNACK2(4), DINNER(5), SNACK3(6);

    private int slot;

    Ingestion(int slot) {
        this.slot = slot;
    }

    public Integer getSlot() {
        return slot;
    }
}
