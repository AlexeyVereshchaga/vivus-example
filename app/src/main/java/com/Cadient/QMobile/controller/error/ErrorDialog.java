package com.Cadient.QMobile.controller.error;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Cadient.QMobile.R;


/**
 * Created by Alexey Vereshchaga on 09.09.14.
 */
public class ErrorDialog extends DialogFragment implements View.OnClickListener {

    public static final String MESSAGE_KEY = "message";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(getResources().getString(R.string.title_error_dialog));

        View v = inflater.inflate(R.layout.fragm_error_dialog, null);
        v.findViewById(R.id.btn_ok).setOnClickListener(this);
        String message = getArguments().getString(MESSAGE_KEY);
        ((TextView) v.findViewById(R.id.tv_header)).setText(message);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                dismiss();
                break;
        }
    }
}
