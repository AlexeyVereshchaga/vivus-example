package com.Cadient.QMobile.handler;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.Cadient.QMobile.events.IRemoteCallbackListener;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

import static com.Cadient.QMobile.utils.ErrorCode.NETWORK_NOT_AVAILABLE;
import static com.Cadient.QMobile.utils.ErrorCode.UNPROCESSABLE_ENTITY;

/**
 * Created by ivan on 11/7/14.
 */
public abstract class BaseRemoteListener<T> implements IRemoteCallbackListener<T> {

    private static final String TAG_NETWORK_NOT_AVAILABLE = "net_work";
    private static final String TAG_UNPROCESSABLE_ENTITY = "unprocessable_entity";
    private static final String NO_INTERNET_CONNECTION = "No internet connection";
    private FragmentActivity activity;

    protected BaseRemoteListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onStartTask() {

    }

    @Override
    public void onSuccess(T result) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onFailure(Integer failureCode) {
        if (failureCode.equals(NETWORK_NOT_AVAILABLE.getCode())) {
            if (activity != null) {
                Crouton.makeText(activity, NO_INTERNET_CONNECTION, Style.ALERT).show();
            }
            Log.e(TAG_NETWORK_NOT_AVAILABLE, NETWORK_NOT_AVAILABLE.getMessage());
        }
        if (failureCode.equals(UNPROCESSABLE_ENTITY.getCode())) {
//            if (activity != null) {
//                Crouton.makeText(activity, UNPROCESSABLE_ENTITY.getMessage(), Style.ALERT).show();
//            }
            Log.e(TAG_UNPROCESSABLE_ENTITY, UNPROCESSABLE_ENTITY.getMessage());
        }
    }

    @Override
    public void onError(T error) {

    }

    @Override
    public void onFinishTask() {

    }
}
