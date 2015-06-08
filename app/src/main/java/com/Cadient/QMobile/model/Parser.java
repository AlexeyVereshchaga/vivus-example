package com.Cadient.QMobile.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Created by Alexey Vereshchaga on 04.09.14.
 */
public class Parser extends BaseModel {
    private String slot;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
