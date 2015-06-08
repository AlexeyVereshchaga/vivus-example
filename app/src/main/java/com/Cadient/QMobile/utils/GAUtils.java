package com.Cadient.QMobile.utils;

import android.content.Context;
import android.util.Log;

import com.Cadient.QMobile.TheApplication;
import com.google.android.gms.analytics.HitBuilders;

/**
 * Created by Alexey Vereshchaga on 04.12.14.
 */
public class GAUtils {

    public static final String GA_KEY = "UA-29478605-23";

    public static final String FIRST_CATEGORY = "Category";
    public static final String LOGIN_ACTION = "ReadyTrackAndroid_Login";
    public static final String ADD_MEAL_ACTION = "ReadyTrackAndroid_AddMeal";
    public static final String ADD_ACTIVITY_ACTION = "ReadyTrackAndroid_AddActivity";
    public static final String ADD_WEIGHT_ACTION = "ReadyTrackAndroid_AddWeight";

    /**
     * @return Application's version code from the {@code PackageManager}.
     */
    public static int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            Log.d(TheApplication.class.getSimpleName(), "Exception: ", e);
        }
        return 0;
    }

    public static void trackEvent(String action) {
        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder()
                .setAction(action)
                .setCategory(GAUtils.FIRST_CATEGORY);
        TheApplication.getInstance().getTracker().send(eventBuilder.build());
    }
}
