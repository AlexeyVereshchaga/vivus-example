package com.Cadient.QMobile.controller.error;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexey Vereshchaga on 09.09.14.
 */
public abstract class AbstractErrorHandler implements ErrorHandler {
    protected Activity activity;
    protected AlertDialog alertDialog;
    protected Set<ErrorHandlerListener> handlerListeners = new HashSet<ErrorHandlerListener>();

    protected AbstractErrorHandler(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void handleError(Object communicationError) {
        manageLoadDialog(false);
    }

    public void handleFail(Object fail) {
        manageLoadDialog(false);
    }

    @Override
    public void manageLoadDialog(boolean show) {
        manageLoadDialog(show, null);
    }

    public void showDialog(String message) {  //, Dialog.OnClickListener listener) {
        ErrorDialog dialog = new ErrorDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ErrorDialog.MESSAGE_KEY, message);
        dialog.setArguments(bundle);
        dialog.show(activity.getFragmentManager(), null);
    }

    public void hideDialog() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    @Override
    public void addListener(ErrorHandlerListener errorHandlerListener) {
        handlerListeners.add(errorHandlerListener);
    }

    @Override
    public void removeListener(ErrorHandlerListener errorHandlerListener) {
        handlerListeners.remove(errorHandlerListener);
    }

    /**
     * call all objects what need know about progress dialog show/hide
     *
     * @param show
     */
    protected void fireProgressDialogShow(boolean show) {
        for (ErrorHandlerListener handlerListener : handlerListeners) {
            handlerListener.progressDialogShow(show);
        }
    }
}
