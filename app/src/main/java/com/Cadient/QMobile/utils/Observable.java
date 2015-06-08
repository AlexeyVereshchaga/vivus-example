package com.Cadient.QMobile.utils;

/**
 * Created by Alexey Vereshchaga on 09.12.14.
 */
public interface Observable {
    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}
