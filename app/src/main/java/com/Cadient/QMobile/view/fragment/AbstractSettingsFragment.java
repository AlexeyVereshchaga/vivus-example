package com.Cadient.QMobile.view.fragment;

import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.Cadient.QMobile.Environment;
import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.view.activity.MainActivity;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 10/7/14.
 */
public abstract class AbstractSettingsFragment extends AbstractFragment<MainActivity> {

    private WebView wvSettings;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_settings;
    }

    @Override
    protected void assignViews(View view) {
        wvSettings = (WebView) view.findViewById(R.id.wv_settings);
        wvSettings.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void initView(View view) {
        String postData = "ulogin=" + TheApplication.getInstance().getUserKey() + "&upassword=" + TheApplication.getInstance().getPasswordKey();
        wvSettings.postUrl(getUrl(), EncodingUtils.getBytes(postData, "UTF-8"));
        wvSettings.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                URL url = null;
                try {
                    url = new URL(getUrl());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                if (url != null && url.getUserInfo() != null) {
                    String[] loginPass = url.getUserInfo().split(":");
                    handler.proceed(loginPass[0], loginPass[1]);
                }
            }

            @Override
            public void onPageFinished(final WebView view, String url) {
                super.onPageFinished(view, url);
                if (getHeaderController() != null && getTransitManager().getCurrentFragment().equals(getCurrentFragment())) {
                    if (view.canGoBack()) {
                        getHeaderController().setLeftButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                view.goBack();
                            }
                        }, true, R.drawable.back_chevron);
                    } else {
                        getHeaderController().setLeftButton(new MenuClickListener(), true, R.drawable.together);
                    }
                    if (view.canGoForward()) {
                        getHeaderController().setRightButton(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                view.goForward();
                            }
                        }, true, R.drawable.forvard_chevron);
                    } else {
                        getHeaderController().setRightButton(null, false, 0);
                    }
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getHeaderController() != null) {
            getHeaderController().setTitle(getResources().getString(getTitleResource()));
            getHeaderController().setLeftButton(new MenuClickListener(), true, R.drawable.together);
        }
    }

    protected abstract String getUrl();

    protected abstract int getTitleResource();

    protected abstract Fragment getCurrentFragment();

    private class MenuClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            toggleDrawer();
        }
    }
}
