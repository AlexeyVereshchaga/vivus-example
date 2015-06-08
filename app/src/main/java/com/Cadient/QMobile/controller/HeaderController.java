package com.Cadient.QMobile.controller;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.view.activity.BaseActivity;
import com.Cadient.QMobile.view.fragment.listener.NotifyCalendarListener;

/**
 * Created by Alexey Vereshchaga on 23.09.14.
 */
public class HeaderController extends AbstractHeaderController {

    private static int LEFT_BTN_ID = R.id.btn_left;
    private static int RIGHT_BTN_ID = R.id.btn_right;
    private static int TITLE_ID = R.id.title;

    private static int ARROW_LEFT_ID = R.id.header_arrow_left;
    private static int ARROW_RIGHT_ID = R.id.header_arrow_right;

    private ImageButton rightButton;
    private ImageButton leftButton;

    private TextView titleView;

    private ImageView arrowLeft;
    private ImageView arrowRight;

    private String title;
    private BaseActivity baseActivity;


    public HeaderController(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }


    @Override
    public void init() {
        rightButton = (ImageButton) viewHeader.findViewById(RIGHT_BTN_ID);
        leftButton = (ImageButton) viewHeader.findViewById(LEFT_BTN_ID);
        arrowLeft = (ImageView) viewHeader.findViewById(ARROW_LEFT_ID);
        arrowRight = (ImageView) viewHeader.findViewById(ARROW_RIGHT_ID);
        titleView = (TextView) viewHeader.findViewById(TITLE_ID);
    }

    public void setRightButton(View.OnClickListener onClickListener, boolean show, int img) {
        if (getRightButton() != null) {
            getRightButton().setOnClickListener(onClickListener);
            getRightButton().setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            if (img != 0) {
                getRightButton().setImageResource(img);
            }
        }
    }

    public void setLeftButton(View.OnClickListener onClickListener, boolean show, int img) {
        if (getLeftButton() != null) {
            getLeftButton().setOnClickListener(onClickListener);
            getLeftButton().setVisibility(show ? View.VISIBLE : View.INVISIBLE);
            if (img != 0) {
                getLeftButton().setImageResource(img);
            }
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
            titleView.setText(title);
        }
    }

    public void setArrowLeft(NotifyCalendarListener onClickListener, boolean show) {
        if (show) {
            if (onClickListener != null) {
                arrowLeft.setOnClickListener(onClickListener);
            }
            baseActivity.notifyArrows();
        } else {
            arrowLeft.setVisibility(View.INVISIBLE);
        }
    }

    public void setArrowRight(NotifyCalendarListener onClickListener, boolean show) {
        if (show) {
            if (onClickListener != null) {
                arrowRight.setOnClickListener(onClickListener);
            }
            baseActivity.notifyArrows();
        } else {
            arrowRight.setVisibility(View.INVISIBLE);
        }
    }

    public RelativeLayout getGeneralHeaderView() {
        return (RelativeLayout) viewHeader;
    }

    public ImageButton getRightButton() {
        return rightButton;
    }

    public ImageButton getLeftButton() {
        return leftButton;
    }

    public ImageView getArrowLeft() {
        return arrowLeft;
    }

    public ImageView getArrowRight() {
        return arrowRight;
    }

    public TextView getTitleView() {
        return titleView;
    }

    public String getTitle() {
        return title;
    }
}
