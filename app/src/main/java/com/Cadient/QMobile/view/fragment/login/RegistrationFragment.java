package com.Cadient.QMobile.view.fragment.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.Cadient.QMobile.R;
import com.Cadient.QMobile.TheApplication;
import com.Cadient.QMobile.utils.HelpUtils;
import com.Cadient.QMobile.utils.Observer;
import com.Cadient.QMobile.utils.ServerHelpUtils;
import com.Cadient.QMobile.view.activity.StartActivity;
import com.Cadient.QMobile.view.fragment.AbstractFragment;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by Alex on 06.10.2014.
 */
public class RegistrationFragment extends AbstractFragment<StartActivity> implements Observer {

    private static final String REDIRECT_URL1 = "dashboard";
    private static final String REDIRECT_URL2 = "dashboard.html";
    private static final String PREVIOUS_URL = "register/";

    private String urlStr;

    private WebView wvRegistration;
    private ImageView btnLeft;
    private ImageView btnRight;

    @Override
    protected int getViewLayout() {
        return R.layout.fragm_registration;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBaseActivity().getObservable().registerObserver(this);
    }

    @Override
    protected void assignViews(View view) {
        wvRegistration = (WebView) view.findViewById(R.id.wv_registration);
        wvRegistration.getSettings().setJavaScriptEnabled(true);
        btnLeft = (ImageView) view.findViewById(R.id.btn_left);
        btnRight = (ImageView) view.findViewById(R.id.btn_right);
        CookieSyncManager.createInstance(getActivity());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }

    @Override
    protected void initView(View view) {
        urlStr = getArguments().getString(HelpUtils.REGISTRATION_KEY);
        setLeftButtonState();
        ((TextView) view.findViewById(R.id.title)).setText(getArguments().getString(HelpUtils.REGISTRATION_HEADER_VALUE, getResources().getString(R.string.registration_header)));
        if (urlStr != null) {
            wvRegistration.loadUrl(urlStr);
        }
        wvRegistration.setWebViewClient(new RegistrationWebViewClient());
    }

    private void setLeftButtonState() {
        btnLeft.setVisibility(View.VISIBLE);
        btnLeft.setImageResource(R.drawable.back_chevron);
        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTransitManager().back();
            }
        });
    }

    @Override
    public void update() {
        if (getArguments().getString(HelpUtils.REGISTRATION_HEADER_VALUE).equals(getResources().getString(R.string.registration_header))) {
            urlStr = TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_SIGNUP_URL_KEY);
        } else if (getArguments().getString(HelpUtils.REGISTRATION_HEADER_VALUE).equals(getResources().getString(R.string.forgot_password_header))) {
            urlStr = TheApplication.getInstance().getServerUrl(ServerHelpUtils.SERVER_FORGOT_PASSWORD_URL_KEY);
        }
        wvRegistration.loadUrl(urlStr);
    }

    @Override
    public void onDetach() {
        getBaseActivity().getObservable().removeObserver(this);
        super.onDetach();
    }

    private class RegistrationWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            URL url1 = null;
            try {
                url1 = new URL(urlStr);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String userInfo = url1.getUserInfo();
            if (userInfo != null) {
                String[] loginPass = userInfo.split(":");
                handler.proceed(loginPass[0], loginPass[1]);
            }
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if ((url.endsWith(REDIRECT_URL1) || url.endsWith(REDIRECT_URL2)) && getTransitManager() != null) {
                getTransitManager().back();
            }
        }

        @Override
        public void onPageFinished(final WebView view, String url) {
            super.onPageFinished(view, url);
            if (view.canGoBack()) {
                btnLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.goBack();
                    }
                });
            } else {
                setLeftButtonState();
            }
            if (view.canGoForward()) {
                btnRight.setVisibility(View.VISIBLE);
                btnRight.setImageResource(R.drawable.forvard_chevron);
                btnRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view.goForward();
                    }
                });
            } else {
                btnRight.setOnClickListener(null);
                btnRight.setVisibility(View.INVISIBLE);
            }
        }
    }
}

