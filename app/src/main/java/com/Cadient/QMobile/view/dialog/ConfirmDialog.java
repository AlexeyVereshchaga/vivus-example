package com.Cadient.QMobile.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.Cadient.QMobile.R;

/**
 * Created by ivan on 10/31/14.
 */
public class ConfirmDialog extends Dialog implements View.OnClickListener {

    private OnDialogCloseListener onCloseListener;
    private static String body;

    public interface OnDialogCloseListener {
        public void OnDialogClose(ConfirmDialogResult result);
    }

    public enum ConfirmDialogResult {
        DELETE,
        CLOSE,
    }

    public void setOnDialogCloseListener(OnDialogCloseListener listener) {
        onCloseListener = listener;
    }

    public ConfirmDialog(Context context) {
        super(context, R.style.alert_login_dialog);
    }

    public static ConfirmDialog showDlg(Context context, String body) {
        ConfirmDialog.body = body;
        ConfirmDialog dialog = new ConfirmDialog(context);
        dialog.show();
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        setCanceledOnTouchOutside(true);
        View view = getLayoutInflater().inflate(R.layout.confirm_dialog, null);
        ((TextView) view.findViewById(R.id.body)).setText(body);
        view.findViewById(R.id.btn_close).setOnClickListener(this);
        view.findViewById(R.id.btn_delete).setOnClickListener(this);

        setContentView(view);
    }

    private void onClose(ConfirmDialogResult result) {
        dismiss();
        onCloseListener.OnDialogClose(result);
    }

    @Override
    protected void onStop() {
        super.onStop();
        onClose(ConfirmDialogResult.CLOSE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                ConfirmDialog.this.onClose(ConfirmDialogResult.CLOSE);
                break;
            case R.id.btn_delete:
                ConfirmDialog.this.onClose(ConfirmDialogResult.DELETE);
                break;
        }
    }
}
