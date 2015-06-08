package com.Cadient.QMobile.view.fragment.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;

import com.Cadient.QMobile.R;

import java.io.InputStream;

/**
 * Created by ivan on 11/26/14.
 */
public class GIFView extends View {

    private Movie mMovie;
    private long moviestart;

    public GIFView(Context context) {
        super(context);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs(attrs);
        initializeView();
    }

    public GIFView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView();
        setAttrs(attrs);
    }

    private int gifId;

    public void setGIFResource(int resId) {
        this.gifId = resId;
        initializeView();
    }

    public int getGIFResource() {
        return this.gifId;
    }

    private void initializeView() {
        if (gifId != 0) {
            InputStream is = getContext().getResources().openRawResource(gifId);
            mMovie = Movie.decodeStream(is);
            moviestart = 0;
            this.invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        super.onDraw(canvas);
        long now = android.os.SystemClock.uptimeMillis();

        if (moviestart == 0) {
            moviestart = now;
        }
        if (mMovie != null) {
            int relTime = (int) ((now - moviestart) % mMovie.duration());
            mMovie.setTime(relTime);
            mMovie.draw(canvas, (getWidth() - mMovie.width()) / 2, ((getHeight() - mMovie.height()) / 2) - addThirtyPercent(getHeight()));
            this.invalidate();
        }
    }

    private float addThirtyPercent(float result) {
        return (result/100) * 25;
    }

    private void setAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.GIFView, 0, 0);
            String gifSource = a.getString(R.styleable.GIFView_src);
            //little workaround here. Who knows better approach on how to easily get resource id - please share
            String sourceName = Uri.parse(gifSource).getLastPathSegment().replace(".gif", "");
            setGIFResource(getResources().getIdentifier(sourceName, "drawable", getContext().getPackageName()));
            a.recycle();
        }
    }
}

