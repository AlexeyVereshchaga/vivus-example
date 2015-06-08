package com.Cadient.QMobile.controller.error;

import android.app.Activity;

/**
 * Created by Alexey Vereshchaga on 09.09.14.
 */
public class LoginActivityErrorHandler extends AbstractErrorHandler {
    public LoginActivityErrorHandler(Activity activity) {
        super(activity);
    }

    @Override
    public void manageLoadDialog(boolean show, String message) {

    }
}
