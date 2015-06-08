package com.Cadient.QMobile.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.Cadient.QMobile.R;


/**
 * Created by Server on 11.11.2014.
 */
public class BlockerDialog extends ProgressDialog {

    public BlockerDialog(Context context) {
        super(context);

    }

    @Override
    public void show() {
        if (!this.isShowing()) {
            super.show();
            setContentView(R.layout.dialog_progress_layout);
        }
    }
}