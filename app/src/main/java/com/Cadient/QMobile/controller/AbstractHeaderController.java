package com.Cadient.QMobile.controller;

import android.view.View;

import com.fsm.transit.core.ITransitManager;

/**
 * Created by Alexey Vereshchaga on 23.09.14.
 */
public abstract class AbstractHeaderController {

    protected ITransitManager transitManager;
    protected View viewHeader;

    public void setTransitManager(ITransitManager transitManager) {
        this.transitManager = transitManager;
    }

    public void setViewHeader(View viewHeader) {
        this.viewHeader = viewHeader;
    }

    public abstract void init();

    public void showHeader(boolean flag) {
        viewHeader.setVisibility(flag ? View.VISIBLE : View.GONE);
    }
}
