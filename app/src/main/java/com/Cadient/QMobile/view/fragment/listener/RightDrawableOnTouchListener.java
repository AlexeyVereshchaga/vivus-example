package com.Cadient.QMobile.view.fragment.listener;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Alexey Vereshchaga on 30.10.14.
 */
public abstract class RightDrawableOnTouchListener implements View.OnTouchListener {
    private EditText view;
    private int fuzz = 10;

    public RightDrawableOnTouchListener(EditText view) {
        super();
        this.view = view;

    }

    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        Drawable drawable = null;
        Drawable[] drawables = view.getCompoundDrawables();
        if (drawables != null && drawables.length == 4)
            drawable = drawables[2];
        if (event.getAction() == MotionEvent.ACTION_DOWN && drawable != null) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            final Rect bounds = drawable.getBounds();
            if (x >= (v.getRight() - bounds.width() - fuzz) && x <= (v.getRight() - v.getPaddingRight() + fuzz)
                    && y >= (v.getPaddingTop() - fuzz) && y <= (v.getHeight() - v.getPaddingBottom()) + fuzz) {
                return onDrawableTouch(event);
            }
        }
        return false;
    }

    public abstract boolean onDrawableTouch(final MotionEvent event);

}
