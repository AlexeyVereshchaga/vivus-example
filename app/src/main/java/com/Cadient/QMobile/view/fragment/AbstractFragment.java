package com.Cadient.QMobile.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.Cadient.QMobile.controller.HeaderController;
import com.Cadient.QMobile.controller.transit.FragmentAction;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.fsm.transit.core.ITransitManager;

import java.util.Calendar;

/**
 * Created by ivan on 3/26/14.
 */
public abstract class AbstractFragment<T extends BaseActivity> extends Fragment {

    protected DrawerLayout drawer;
    protected LinearLayout drawerLayout;

    private T baseActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseActivity().setCalendar(Calendar.getInstance());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getViewLayout(), container, false);
        assignViews(view);
        initView(view);
        //todo need refactor
        if (getActivity() instanceof MainActivity) {
            drawer = ((MainActivity) getActivity()).getDrawer();
            drawerLayout = ((MainActivity) getActivity()).getDrawerLayout();
        }
        return view;
    }

    protected void hideDrawer() {
        if (drawerLayout != null && drawer != null) {
            if (drawer.isDrawerOpen(drawerLayout)) {
                drawer.closeDrawer(drawerLayout);
            }
        }
    }

    protected void toggleDrawer() {
        if (drawerLayout != null && drawer != null) {
            if (drawer.isDrawerOpen(drawerLayout)) {
                drawer.closeDrawer(drawerLayout);
            } else {
                drawer.openDrawer(drawerLayout);
            }
        }
    }

    protected void assignViews(View view) {
    }

    protected void initView(View view) {
    }

    protected abstract int getViewLayout();

    /**
     * special fragment manager, do all switch use this object.
     *
     * @return
     */
    public ITransitManager<FragmentAction> getTransitManager() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getTransitManager();
        } else {
            return null;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        baseActivity = (T) activity;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getHeaderController() != null) {
            getHeaderController().setTitle("");
            getHeaderController().setArrowLeft(null, false);
            getHeaderController().setArrowRight(null, false);
            getHeaderController().setLeftButton(null, false, 0);
            getHeaderController().setRightButton(null, false, 0);
            getHeaderController().getGeneralHeaderView().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity().getCurrentFocus() != null) {
            Object inputMethodManager = getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                ((InputMethodManager) inputMethodManager).hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    public HeaderController getHeaderController() {
        if (getActivity() != null) {
            return ((BaseActivity) getActivity()).getHeaderController();
        } else {
            return null;
        }
    }

    public T getBaseActivity() {
        return baseActivity;
    }
}
