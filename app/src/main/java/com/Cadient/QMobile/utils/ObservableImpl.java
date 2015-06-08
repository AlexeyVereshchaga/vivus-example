package com.Cadient.QMobile.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 09.12.14.
 */
public class ObservableImpl implements Observable {

    private List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
