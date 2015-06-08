package com.Cadient.QMobile;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.Cadient.QMobile.utils.GAUtils;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.Date;

/**
 * Created by alexey on 27.08.14.
 */
public class TheApplication extends Application {

    private static final String USER_KEY = "com.Cadient.QMobile.user";
    private static final String PASSWORD_KEY = "com.Cadient.QMobile.password";
    private static final String REGISTRATION_DATE_KEY = "com.Cadient.QMobile.reg_date";
    private Tracker tracker;

    private static TheApplication sApplication;

    private SharedPreferences sharedPreferences;

    public static TheApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
//       DataBaseGateway.getInstance().init(this);
    }

    public synchronized Tracker getTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            analytics.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
            tracker = analytics.newTracker(GAUtils.GA_KEY);
            tracker.setAppName(getString(R.string.app_name));
            tracker.setAppVersion(String.valueOf(GAUtils.getAppVersion(this)));
        }
        return tracker;
    }

    public SharedPreferences getPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        return sharedPreferences;
    }

    public String getUserKey() {
        return getPreferences().getString(USER_KEY, null);
    }

    public String getPasswordKey() {
        return getPreferences().getString(PASSWORD_KEY, null);
    }

    public String getServerUrl(String serverUrlKey) {
        return getPreferences().getString(serverUrlKey, null);
    }

    public Date getRegistrationDate() {
        return new Date(getPreferences().getLong(REGISTRATION_DATE_KEY, 0));
    }

    public void setRegistrationDate(long date) {
        getPreferences().edit().putLong(REGISTRATION_DATE_KEY, date).apply();
    }

    public void setUserKey(String userKey) {
        getPreferences().edit().putString(USER_KEY, userKey).apply();
    }

    public void setPasswordKey(String password) {
        getPreferences().edit().putString(PASSWORD_KEY, password).apply();
    }

    public void setServerUrl(String serverUrlKey, String serverUrl) {
        getPreferences().edit().putString(serverUrlKey, serverUrl).apply();
    }

    public void deleteUserKey() {
        getPreferences().edit().remove(USER_KEY).commit();
    }

    public void deletePasswordKey() {
        getPreferences().edit().remove(PASSWORD_KEY).commit();
    }

    public void deleteRegistrationDate() {
        getPreferences().edit().remove(REGISTRATION_DATE_KEY).commit();
    }

    public void deleteServerUrl(String serverUrlKey) {
        getPreferences().edit().remove(serverUrlKey).commit();
    }
}
