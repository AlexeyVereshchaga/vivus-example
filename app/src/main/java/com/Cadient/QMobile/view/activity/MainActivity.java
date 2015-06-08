package com.Cadient.QMobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.controller.transit.MainTransitManager;
import com.Cadient.QMobile.handler.BaseHandler;
import com.Cadient.QMobile.handler.BaseRemoteListener;
import com.Cadient.QMobile.model.remote.RegistrationResult;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Server;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.adapter.DrawerAdapter;
import com.Cadient.QMobile.view.fragment.AboutReadyTrackFragment;
import com.Cadient.QMobile.view.fragment.DashboardFragment;
import com.Cadient.QMobile.view.fragment.SettingsFragment;
import com.Cadient.QMobile.view.fragment.WeightTrackingFragment;
import com.Cadient.QMobile.view.fragment.exercisetrack.ExerciseTrackingFragment;
import com.Cadient.QMobile.view.fragment.mealtrack.MealTrackingFragment;
import com.Cadient.QMobile.view.fragment.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey Vereshchaga on 08.09.14.
 */
public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private DrawerLayout drawer;
    private DrawerAdapter drawerAdapter;
    private ListView list_items;
    private LinearLayout drawerLayout;
    private LinearLayout btnLogOut;

    private Class<? extends android.support.v4.app.Fragment> aClass;

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public LinearLayout getDrawerLayout() {
        return drawerLayout;
    }

    private static List<MenuItem> menuItems = new ArrayList<MenuItem>() {
        {
            add(new MenuItem(DashboardFragment.class, R.drawable.ic_dashboard, "Dashboard"));
            add(new MenuItem(MealTrackingFragment.class, R.drawable.ic_meal_tracking, "Meal Tracking"));
            add(new MenuItem(ExerciseTrackingFragment.class, R.drawable.ic_exercise_tracking, "Exercise Tracking"));
            add(new MenuItem(WeightTrackingFragment.class, R.drawable.ic_weight_tracking, "Weight Tracking"));
            add(new MenuItem(SettingsFragment.class, R.drawable.my_account, "My Settings"));
            add(new MenuItem(AboutReadyTrackFragment.class, R.drawable.ic_about_ready_track, "About Ready Track"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerLayout = (LinearLayout) findViewById(R.id.drawer_layout);
        list_items = (ListView) findViewById(R.id.list_items);
        btnLogOut = (LinearLayout) findViewById(R.id.btn_log_out);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TheApplication.getInstance().deletePasswordKey();
                TheApplication.getInstance().deleteUserKey();
                TheApplication.getInstance().deleteRegistrationDate();
                ServerHelpUtils.deleteAllServerUrls();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                MainActivity.this.finish();
            }
        });

        drawerAdapter = new DrawerAdapter(this, R.layout.drawer_list_item);
        list_items.setAdapter(drawerAdapter);
        list_items.setOnItemClickListener(this);
        drawerAdapter.addAll(menuItems);
        overrideDrawerListener();

        getDataManager().getRegistrationResult(
                new BaseHandler<RegistrationResult>(new BaseRemoteListener<RegistrationResult>(this) {
                    @Override
                    public void onSuccess(RegistrationResult result) {
                        TheApplication.getInstance().setRegistrationDate((HelpUtils.parseRegistrStringToDate(result.getData().getRegistrationDate())).getTime());
                        notifyArrows();
                    }
                }, RegistrationResult.class)
        );
    }

    @Override
    protected int getActivityLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMainFragmentContainerRes() {
        return R.id.fragment_container;
    }

    @Override
    protected void createTransitManager() {
        transitManager = new MainTransitManager(this);
    }

    @Override
    protected Class<? extends Fragment> getFragmentForStart() {
        return DashboardFragment.class;
    }

    @Override
    protected int getHeaderViewIdRes() {
        return R.id.header;
    }

    @Override
    protected void createErrorHandler() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        aClass = ((MenuItem) parent.getItemAtPosition(position)).getFragmentClass();
        drawer.closeDrawer(drawerLayout);
        ((ViewGroup) getTransitManager().getCurrentFragment().getView()).removeAllViews();
    }

    private void overrideDrawerListener() {
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View view, float v) {
            }

            @Override
            public void onDrawerOpened(View view) {
                MainActivity.this.hideVirtualKeyboard();
            }

            @Override
            public void onDrawerClosed(View view) {
                MainActivity.this.hideVirtualKeyboard();
                if (aClass != null) {
                    getTransitManager().switchBranch(aClass);
                    aClass = null;
                }
            }

            @Override
            public void onDrawerStateChanged(int i) {
            }
        });
    }

    public void hideVirtualKeyboard() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawer != null) {
            if (drawer.isDrawerOpen(drawerLayout)) {
                drawer.closeDrawer(drawerLayout);
            }
        }
        super.onBackPressed();
    }
}
