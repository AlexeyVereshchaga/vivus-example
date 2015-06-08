package com.Cadient.QMobile.view.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.controller.error.LoginActivityErrorHandler;
import com.Cadient.QMobile.controller.transit.StartTransitManager;
import com.Cadient.QMobile.utils.ObservableImpl;
import com.Cadient.QMobile.utils.Server;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.fragment.login.LoginFragment;

/**
 * Created by alexey on 12.08.14.
 */
public class StartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //erase server urls after start app
        ServerHelpUtils.deleteAllServerUrls();
        observable = new ObservableImpl();
    }

    @Override
    protected int getActivityLayoutRes() {
        return R.layout.activity_start;
    }

    @Override
    protected int getMainFragmentContainerRes() {
        return R.id.fragment_container;
    }

    @Override
    protected void createTransitManager() {
        transitManager = new StartTransitManager(this);
    }

    protected Class<? extends Fragment> getFragmentForStart() {
        return LoginFragment.class;
    }

    @Override
    protected void createErrorHandler() {
        errorHandler = new LoginActivityErrorHandler(this);
    }

}
