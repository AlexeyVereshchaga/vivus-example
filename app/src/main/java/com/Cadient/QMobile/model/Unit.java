package com.Cadient.QMobile.model;

/**
 * Created by Alexey Vereshchaga on 02.09.14.
 */
public class Unit extends BaseModel {

    private Integer id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
