package com.Cadient.QMobile.view.fragment;

import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.controller.HeaderController;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.activity.MainActivity;
import com.Cadient.QMobile.view.adapter.CalendarCaldroidAdapter;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

/**
 * Created by Alexey Vereshchaga on 10.10.14.
 */
public class CalendarFragment extends CaldroidFragment {

    private MainActivity activity;

    @Override
    public void onResume() {
        super.onResume();

        //todo quick fix, need refactor
        activity = (MainActivity) getActivity();
        // hideDrawer(activity.getDrawerLayout(), activity.getDrawer());

        HeaderController headerController = ((BaseActivity) getActivity()).getHeaderController();
        if (headerController != null) {
            headerController.getGeneralHeaderView().setVisibility(View.VISIBLE);
            headerController.setTitle(getString(R.string.calendar));
            headerController.setLeftButton(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BaseActivity) getActivity()).getTransitManager().back();
                }
            }, true, R.drawable.arrow_back);
            headerController.setRightButton(null, false, 0);
            headerController.setArrowLeft(null, false);
            headerController.setArrowRight(null, false);
        }
        activity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.getDrawer().setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        return new CalendarCaldroidAdapter(getActivity(), month, year, getCaldroidData(), null);
    }

//    private void hideDrawer(LinearLayout drawerLayout, DrawerLayout drawer) {
//        if (drawerLayout != null && drawer != null) {
//            if (drawer.isDrawerOpen(drawerLayout)) {
//                drawer.closeDrawer(drawerLayout);
//            }
//        }
//    }
}