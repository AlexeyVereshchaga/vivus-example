package com.Cadient.QMobile.view.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.activity.MainActivity;

/**
 * Created by ivan on 10/7/14.
 */
public class AboutReadyTrackFragment extends AbstractSettingsFragment {

    private String urlSuffix = "ready-track.html";

    @Override
    protected String getUrl() {
        return TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_ABOUT_URL_KEY);
    }

    @Override
    protected int getTitleResource() {
        return R.string.about_ready;
    }

    @Override
    protected Fragment getCurrentFragment() {
        return this;
    }
}