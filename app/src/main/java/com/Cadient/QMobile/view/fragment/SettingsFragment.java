package com.Cadient.QMobile.view.fragment;

import android.support.v4.app.Fragment;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.utils.ServerHelpUtils;

/**
 * Created by Alexey Vereshchaga on 26.11.14.
 */
public class SettingsFragment extends AbstractSettingsFragment {

    private String urlSuffix = "settings/";

    @Override
    protected String getUrl() {
        return TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_MY_ACCOUNT_URL_KEY);
    }

    @Override
    protected int getTitleResource() {
        return R.string.my_account_title_txt;
    }

    @Override
    protected Fragment getCurrentFragment() {
        return this;
    }
}
