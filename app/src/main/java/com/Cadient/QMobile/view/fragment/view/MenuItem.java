package com.Cadient.QMobile.view.fragment.view;

import android.support.v4.app.Fragment;

/**
 * Created by ivan on 3/26/14.
 */
public class MenuItem {

    private Class<? extends Fragment> fragmentClass;
    private int icon;
    private int activeIcon;
    private String title;

    public MenuItem(Class<? extends Fragment> fragmentClass, int icon, String title) {
        this.fragmentClass = fragmentClass;
        this.icon = icon;
        this.title = title;
    }

    public Class<? extends Fragment> getFragmentClass() {
        return fragmentClass;
    }

    public void setFragmentClass(Class<? extends Fragment> fragmentClass) {
        this.fragmentClass = fragmentClass;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
